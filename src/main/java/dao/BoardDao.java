package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import dto.BoardDto;
import dto.BoardVotesDto;
import entity.Board;
import vo.PageVo;

public class BoardDao {	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<Board> boardMapper =
		new RowMapper<Board>() {
			@Override
			public Board mapRow(ResultSet rs, int rowNum)
				throws SQLException {
				Board board = new Board(
						rs.getString("TITLE"),
						rs.getString("CONTENT"),
						rs.getLong("WRITER"),
						rs.getTimestamp("WRITTEN_DATE").toLocalDateTime(),
						rs.getBoolean("IS_NOTICE"));
				board.setBoardId(rs.getLong("BOARDID"));
				return board;
			}
		};
		
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	// CREATE
	
	public void insert(Board board) {
		String sql = "insert into BOARD (TITLE, CONTENT, WRITER ,WRITTEN_DATE, VIEWS, TYPE, IS_NOTICE) " +
				"values (?, ?, ?, ?, ?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"BOARDID"});
				pstmt.setString(1, board.getTitle());
				pstmt.setString(2, board.getContent());
				pstmt.setLong(3, board.getWriter());
				pstmt.setTimestamp(4, Timestamp.valueOf(board.getWrittenDate()));
				pstmt.setInt(5, board.getViews());
				pstmt.setInt(6, board.getType());
				pstmt.setBoolean(7, board.getIsNotice());
				return pstmt;
			}
		}, keyHolder);
		Number keyValue = keyHolder.getKey();
		board.setBoardId(keyValue.longValue());
		
	}

	// UPDATE

	public void updateViews(long boardId) {
		String sql = "update BOARD set VIEWS=VIEWS+1 where BOARDID=?";
		Object param = boardId;
		jdbcTemplate.update(sql,param);
	}
	
	public void updatePost(BoardDto boardDto) {
		String sql = "update BOARD set TITLE=?, CONTENT=?, TYPE=? where BOARDID=?";
		Object[] params = {boardDto.getTitle(), 
							boardDto.getContent(),
							boardDto.getType(),
							boardDto.getBoardId()};
		jdbcTemplate.update(sql, params);
	}
	
	// READ
	
	public List<Board> selectAll() {
		String sql = "select * from BOARD";
		List<Board> results = jdbcTemplate.query(sql, boardMapper);
		return results;
	}
	
	public List<BoardDto> selectList(int type ,String field, String keyword, int limit, int offset){
		String sql = "SELECT B.*, U.NICKNAME, IFNULL(SUM(V.UP),0) AS UP, IFNULL(SUM(V.DOWN),0) AS DOWN "
				+ "FROM BOARD B "
				+ "LEFT OUTER JOIN USER U "
				+ "ON B.WRITER = U.USERID "
				+ "LEFT OUTER JOIN BOARD_VOTES V "
				+ "ON B.BOARDID = V.BOARDID "
				+ "WHERE B.TYPE = "+type+" AND B.IS_NOTICE = 0 ";
		
				if(keyword != null && !keyword.isBlank() && !keyword.isEmpty()) {
					sql += " AND " +field + " LIKE '%"+ keyword +"%'";
				}
		
				sql += " GROUP BY B.BOARDID "
				+ "ORDER BY BOARDID DESC "
				+ "LIMIT "+ limit +" OFFSET "+offset+";";
			
		try {
			List<BoardDto> result = jdbcTemplate.query(sql, 
					new RowMapper<BoardDto>(){
						@Override
						public BoardDto mapRow(ResultSet rs, int rowNum) throws SQLException {
							BoardVotesDto votes = new BoardVotesDto();
							votes.setUp(rs.getInt("UP"));
							votes.setDown(rs.getInt("DOWN"));
												
							BoardDto boardDto = new BoardDto();
							boardDto.setBoardId(rs.getLong("BOARDID"));
							boardDto.setTitle(rs.getString("TITLE"));
							boardDto.setWriterName(rs.getString("NICKNAME"));
							boardDto.setWrittenDate(rs.getTimestamp("WRITTEN_DATE").toLocalDateTime());
							boardDto.setViews(rs.getInt("VIEWS"));
							boardDto.setVotes(votes);
							
							return boardDto;
					}});
			return result;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public BoardDto showDetail(long boardId){
		String sql = "select B.*, U.NICKNAME "
				+ "from USER U INNER JOIN BOARD B "
				+ "ON U.USERID=B.WRITER "
				+ "where B.BOARDID=?";
		try {
			BoardDto result = jdbcTemplate.queryForObject(sql, 
					new RowMapper<BoardDto>(){
						@Override
						public BoardDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			
							BoardDto boardDto = new BoardDto();
							boardDto.setBoardId(rs.getLong("BOARDID"));
							boardDto.setTitle(rs.getString("TITLE"));
							boardDto.setContent(rs.getString("CONTENT"));
							boardDto.setWriter(rs.getLong("WRITER"));
							boardDto.setWriterName(rs.getString("NICKNAME"));
							boardDto.setWrittenDate(rs.getTimestamp("WRITTEN_DATE").toLocalDateTime());
							boardDto.setViews(rs.getInt("VIEWS"));
							boardDto.setType(rs.getInt("TYPE"));
							return boardDto;
					}},boardId);
			return result;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public List<BoardDto> selectHotPosts(int type, PageVo pageVo) {
		String sql = "SELECT B.*, U.NICKNAME, IFNULL(SUM(V.UP),0) AS UP "
				+ "FROM BOARD B "
				+ "LEFT OUTER JOIN USER U "
				+ "ON B.WRITER = U.USERID "
				+ "LEFT OUTER JOIN BOARD_VOTES V "
				+ "ON B.BOARDID = V.BOARDID "
				+ "WHERE B.TYPE = " + type + " AND UP >= 10 AND DATEDIFF(NOW(), WRITTEN_DATE) < 30 "
				+ "GROUP BY B.BOARDID "
				+ "ORDER BY UP DESC, BOARDID DESC "
				+ "LIMIT 3;";
		try {
			List<BoardDto> result = jdbcTemplate.query(sql, 
					new RowMapper<BoardDto>(){
						@Override
						public BoardDto mapRow(ResultSet rs, int rowNum) throws SQLException {
							BoardVotesDto votes = new BoardVotesDto();
							votes.setUp(rs.getInt("UP"));
							
							BoardDto boardDto = new BoardDto();
							boardDto.setBoardId(rs.getLong("BOARDID"));
							boardDto.setTitle(rs.getString("TITLE"));
							boardDto.setContent(rs.getString("CONTENT"));
							boardDto.setWriter(rs.getLong("WRITER"));
							boardDto.setWriterName(rs.getString("NICKNAME"));
							boardDto.setWrittenDate(rs.getTimestamp("WRITTEN_DATE").toLocalDateTime());
							boardDto.setViews(rs.getInt("VIEWS"));
							boardDto.setType(rs.getInt("TYPE"));
							
							boardDto.setVotes(votes);
							return boardDto;
					}});
			return result;
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
		
	}
	
	public List<BoardDto> selectNotice(int type, PageVo pageVo) {
		String sql = "SELECT B.*, U.NICKNAME, IFNULL(SUM(V.UP),0) AS UP "
				+ "FROM BOARD B "
				+ "LEFT OUTER JOIN USER U "
				+ "ON B.WRITER = U.USERID "
				+ "LEFT OUTER JOIN BOARD_VOTES V "
				+ "ON B.BOARDID = V.BOARDID "
				+ "WHERE TYPE = " + type + " AND IS_NOTICE = 1 "
				+ "GROUP BY BOARDID "
				+ "ORDER BY BOARDID DESC ";
		try {
			List<BoardDto> result = jdbcTemplate.query(sql, 
					new RowMapper<BoardDto>(){
						@Override
						public BoardDto mapRow(ResultSet rs, int rowNum) throws SQLException {
							BoardVotesDto votes = new BoardVotesDto();
							votes.setUp(rs.getInt("UP"));
							
							BoardDto boardDto = new BoardDto();
							boardDto.setBoardId(rs.getLong("BOARDID"));
							boardDto.setTitle(rs.getString("TITLE"));
							boardDto.setContent(rs.getString("CONTENT"));
							boardDto.setWriterName(rs.getString("NICKNAME"));
							boardDto.setWrittenDate(rs.getTimestamp("WRITTEN_DATE").toLocalDateTime());
							boardDto.setViews(rs.getInt("VIEWS"));
							boardDto.setType(rs.getInt("TYPE"));
							
							boardDto.setVotes(votes);
							return boardDto;
					}});
			return result;
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
		
	}
	
	// DELETE
	public void delete(long boardId) {
		String sql = "DELETE FROM BOARD WHERE BOARDID=?";
		jdbcTemplate.update(sql, boardId);
	}
	
	public int count(int type,String field, String keyword) {
		String sql = "SELECT COUNT(*) "
				+ "FROM BOARD B "
				+ "LEFT OUTER JOIN USER U "
				+ "ON B.WRITER = U.USERID "
				+ "WHERE B.TYPE = "+type;
				if(keyword!=null) {
					if(!keyword.isBlank() && !keyword.isEmpty()) {
						sql += " AND "+ field +" LIKE "+ "'%"+ keyword +"%'";
					}
				}
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
}
