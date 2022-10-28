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
import dto.BoardListResponse;
import dto.BoardModifyRequest;
import dto.BoardVotesDto;
import entity.Board;
import entity.User;
import vo.PageVo;

/**
 * SQL쿼리문을 통해 BOARD 테이블로부터 데이터를 받아오는 기능을 수행하는 클래스
 * 
 * @author a
 *
 */

public class BoardDao {	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
		
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/**
	 * 게시글 목록에서 보여지는 정보들을 매핑하는 객체
	 * 
	 */
	
	private RowMapper<BoardDto> boardListMapper = new RowMapper<BoardDto>(){
		@Override
		public BoardDto mapRow(ResultSet rs, int rowNum) throws SQLException {					
			BoardDto boardDto = new BoardDto();
			boardDto.setBoardId(rs.getLong("BOARDID"));
			boardDto.setTitle(rs.getString("TITLE"));
			boardDto.setWrittenDate(rs.getTimestamp("WRITTEN_DATE").toLocalDateTime());
			boardDto.setViews(rs.getInt("VIEWS"));
			
			if(rs.getBoolean("DELETED_WRITER")) {
				boardDto.setWriterName("(알 수 없음)");
			} else {
				boardDto.setWriterName(rs.getString("NICKNAME"));	
			}
			
			BoardVotesDto votes = new BoardVotesDto();
			votes.setUp(rs.getInt("UP"));
			boardDto.setVotes(votes);
			
			return boardDto;
		}
	};
	
	// CREATE
	
	public void insert(Board board) {
		String sql = "INSERT INTO BOARD (TITLE, CONTENT, WRITER ,WRITTEN_DATE, VIEWS, TYPE, IS_NOTICE) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, board.getTitle());
				pstmt.setString(2, board.getContent());
				pstmt.setLong(3, board.getWriter());
				pstmt.setTimestamp(4, Timestamp.valueOf(board.getWrittenDate()));
				pstmt.setInt(5, board.getViews());
				pstmt.setInt(6, board.getType());
				pstmt.setBoolean(7, board.getIsNotice());
				return pstmt;
			}
		});
	}

	// UPDATE
	
	/**
	 * boardId가 일치하는 게시글의 조회수를 1 증가시키는 메서드
	 * 
	 * @param boardId
	 */
	
	public void updateViews(Long boardId) {
		String sql = "UPDATE BOARD SET VIEWS = VIEWS+1 WHERE BOARDID = ?";
		Object param = boardId;
		jdbcTemplate.update(sql,param);
	}
	
	/**
	 * boardId가 일치하는 게시글의 제목, 내용, 카테고리를 수정하는 메서드
	 * 
	 * @param req
	 */
	
	public void updatePost(BoardModifyRequest req) {
		String sql = "UPDATE BOARD SET TITLE = ?, CONTENT = ?, TYPE = ? WHERE BOARDID = ?";
		Object[] params = {req.getNewTitle(), 
				req.getNewContent(),
				req.getType(),
				req.getBoardId()};
		jdbcTemplate.update(sql, params);
	}
	
	// READ
	
	/**
	 * boardId가 일치하는 게시글이 존재하는지 반환하는 메서드
	 * 
	 * @param boardId
	 * @return
	 */
	
	public boolean isExist(Long boardId) {
		String sql = "SELECT COUNT(*) FROM BOARD WHERE BOARDID = ?";
		int num = jdbcTemplate.queryForObject(sql, Integer.class, boardId);
		
		return num == 0 ? false : true;
	}
	
	/**
	 * 검색 조건에 일치하는 공지가 아닌 게시글의 수를 반환하는 메서드
	 * 
	 * @param field
	 * @param keyword
	 * @return
	 */
	
	public int count(String field, String keyword) {
		String sql = "SELECT COUNT(*) "
				+ "FROM BOARD B "
				+ "LEFT OUTER JOIN USER U "
				+ "ON B.WRITER = U.USERID ";
		if(keyword != null && !keyword.isBlank()) {
			sql += "WHERE " + field + " LIKE " + "'%" + keyword + "%'";
		}
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	/**
	 * 검색 조건에 일치하는 공지가 아닌 게시글의 수를 반환하는 메서드
	 * 
	 * @param type
	 * @param field
	 * @param keyword
	 * @return
	 */
	
	public int countWithType(Integer type, String field, String keyword) {
		String sql = "SELECT COUNT(*) "
				+ "FROM BOARD B "
				+ "LEFT OUTER JOIN USER U "
				+ "ON B.WRITER = U.USERID "
				+ "WHERE B.TYPE = ? ";
		if(keyword != null && !keyword.isBlank()) {
			sql += "AND " + field + " LIKE " + "'%" + keyword + "%'";
		}
		
		return jdbcTemplate.queryForObject(sql, Integer.class, type);
	}
	
	/**
	 * 검색 조건에 일치하는 역대 인기게시글의 수를 반환하는 메서드
	 * 
	 * @param field
	 * @param keyword
	 * @return
	 */
	
	public int countHot(String field, String keyword) {
		String sql ="SELECT COUNT(*) "
				+ "FROM(SELECT B.*, U.NICKNAME, IFNULL(SUM(V.UP),0) AS UP "
				+ "FROM BOARD B "
				+ "LEFT OUTER JOIN USER U "
				+ "ON B.WRITER = U.USERID "
				+ "LEFT OUTER JOIN BOARD_VOTES V "
				+ "ON B.BOARDID = V.BOARDID "
				+ "WHERE UP >= 10 ";
		if(keyword != null && !keyword.isBlank()) {
			sql += "AND " + field + " LIKE '%" + keyword + "%' ";
		}
		sql += "GROUP BY B.BOARDID) AS C";
		
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	/**
	 * 공지가 아닌 게시글들을 조건(검색어, 페이지)에 맞게 불러오는 메서드
	 * 
	 * @param field
	 * @param keyword
	 * @param limit
	 * @param offset
	 * @return
	 */
	
	public List<BoardDto> selectPosts(String field, String keyword, Integer limit, Integer offset){
		String sql = "SELECT B.*, U.NICKNAME, U.IS_DELETED AS DELETED_WRITER, IFNULL(SUM(V.UP),0) AS UP, IFNULL(SUM(V.DOWN),0) AS DOWN "
				+ "FROM BOARD B "
				+ "LEFT OUTER JOIN USER U "
				+ "ON B.WRITER = U.USERID "
				+ "LEFT OUTER JOIN BOARD_VOTES V "
				+ "ON B.BOARDID = V.BOARDID "
				+ "WHERE B.IS_NOTICE = 0 ";
		
		if(keyword != null && !keyword.isBlank()) {
			sql += "AND " +field + " LIKE '%"+ keyword +"%' ";
		}
		sql += "GROUP BY B.BOARDID "
			+ "ORDER BY BOARDID DESC "
			+ "LIMIT ? OFFSET ?";
			
		try {
			List<BoardDto> result = jdbcTemplate.query(sql, boardListMapper, limit, offset);
			
			return result;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	/**
	 * 공지가 아닌 게시글들을 조건(카테고리, 검색어, 페이지)에 맞게 불러오는 메서드
	 * 
	 * @param type
	 * @param field
	 * @param keyword
	 * @param limit
	 * @param offset
	 * @return
	 */
	
	public List<BoardDto> selectPosts(Integer type ,String field, String keyword, Integer limit, Integer offset){
		String sql = "SELECT B.*, U.NICKNAME, U.IS_DELETED AS DELETED_WRITER, IFNULL(SUM(V.UP),0) AS UP, IFNULL(SUM(V.DOWN),0) AS DOWN "
				+ "FROM BOARD B "
				+ "LEFT OUTER JOIN USER U "
				+ "ON B.WRITER = U.USERID "
				+ "LEFT OUTER JOIN BOARD_VOTES V "
				+ "ON B.BOARDID = V.BOARDID "
				+ "WHERE B.TYPE = ? AND B.IS_NOTICE = 0 ";
		
		if(keyword != null && !keyword.isBlank()) {
			sql += "AND " +field + " LIKE '%"+ keyword +"%' ";
		}
		sql += "GROUP BY B.BOARDID "
				+ "ORDER BY BOARDID DESC "
				+ "LIMIT ? OFFSET ?";
			
		try {
			List<BoardDto> result = jdbcTemplate.query(sql, boardListMapper, type, limit, offset);
			
			return result;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	/**
	 * 역대 인기글들을 조건(검색어, 페이지)에 맞게 불러오는 메서드 
	 * 
	 * @param field
	 * @param keyword
	 * @param limit
	 * @param offset
	 * @return
	 */
	
	public List<BoardDto> selectAllHotPosts(String field, String keyword, Integer limit, Integer offset) {
		String sql = "SELECT B.*, U.NICKNAME, U.IS_DELETED AS DELETED_WRITER, IFNULL(SUM(V.UP),0) AS UP "
				+ "FROM BOARD B "
				+ "LEFT OUTER JOIN USER U "
				+ "ON B.WRITER = U.USERID "
				+ "LEFT OUTER JOIN BOARD_VOTES V "
				+ "ON B.BOARDID = V.BOARDID "
				+ "WHERE UP >= 10 ";
		if(keyword != null && !keyword.isBlank()) {
			sql += "AND " + field + " LIKE '%" + keyword + "%' ";
		}
		sql += "GROUP BY BOARDID "
				+ "ORDER BY UP DESC, BOARDID DESC "
				+ "LIMIT ? OFFSET ?";
		
		try {
			List<BoardDto> result = jdbcTemplate.query(sql, boardListMapper , limit, offset);
			
			return result;
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
		
	}
	
	/**
	 * 카테고리 상관없이 30일 이내의 상위5개 인기글들을 불러오는 메서드
	 * 
	 * @return
	 */
	
	public List<BoardDto> selectHotPosts() {
		String sql = "SELECT B.*, U.NICKNAME, U.IS_DELETED AS DELETED_WRITER, IFNULL(SUM(V.UP),0) AS UP "
				+ "FROM BOARD B "
				+ "LEFT OUTER JOIN USER U "
				+ "ON B.WRITER = U.USERID "
				+ "LEFT OUTER JOIN BOARD_VOTES V "
				+ "ON B.BOARDID = V.BOARDID "
				+ "WHERE UP >= 10 AND DATEDIFF(NOW(), WRITTEN_DATE) < 30 "
				+ "GROUP BY B.BOARDID "
				+ "ORDER BY UP DESC, BOARDID DESC "
				+ "LIMIT 5";
		
		try {
			List<BoardDto> result = jdbcTemplate.query(sql,	boardListMapper);
			
			return result;
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
		
	}
	
	/**
	 * 30일 이내의 상위 3개 인기글들을 조건(카테고리)에 맞게 불러오는 메서드
	 *  
	 * @param type
	 * @return
	 */
	
	public List<BoardDto> selectHotPosts(Integer type) {
		String sql = "SELECT B.*, U.NICKNAME, U.IS_DELETED AS DELETED_WRITER, IFNULL(SUM(V.UP),0) AS UP "
				+ "FROM BOARD B "
				+ "LEFT OUTER JOIN USER U "
				+ "ON B.WRITER = U.USERID "
				+ "LEFT OUTER JOIN BOARD_VOTES V "
				+ "ON B.BOARDID = V.BOARDID "
				+ "WHERE B.TYPE = ? AND UP >= 10 AND DATEDIFF(NOW(), WRITTEN_DATE) < 30 "
				+ "GROUP BY B.BOARDID "
				+ "ORDER BY UP DESC, BOARDID DESC "
				+ "LIMIT 3";
		
		try {
			List<BoardDto> result = jdbcTemplate.query(sql, boardListMapper, type);
			
			return result;
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
		
	}
	
	/**
	 * 공지글들을 조건(카테고리, 검색어, 페이지)에 맞게 불러오는 메서드
	 * 
	 * @param type
	 * @return
	 */
	
	public List<BoardDto> selectNotices(Integer type) {
		String sql = "SELECT B.*, U.NICKNAME, U.IS_DELETED AS DELETED_WRITER, IFNULL(SUM(V.UP),0) AS UP "
				+ "FROM BOARD B "
				+ "LEFT OUTER JOIN USER U "
				+ "ON B.WRITER = U.USERID "
				+ "LEFT OUTER JOIN BOARD_VOTES V "
				+ "ON B.BOARDID = V.BOARDID "
				+ "WHERE TYPE = ? AND IS_NOTICE = 1 "
				+ "GROUP BY BOARDID "
				+ "ORDER BY BOARDID DESC ";
		
		try {
			List<BoardDto> result = jdbcTemplate.query(sql, boardListMapper, type);
			
			return result;
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
		
	}
	
	/**
	 * 선택한 boardId를 가진 게시글의 상세보기를 불러오는 메서드
	 * 
	 * @param boardId
	 * @return
	 */
	
	public BoardDto showDetail(Long boardId){
		String sql = "select B.*, U.NICKNAME, U.IS_DELETED AS DELETED_WRITER "
				+ "from USER U INNER JOIN BOARD B "
				+ "ON U.USERID = B.WRITER "
				+ "where B.BOARDID = ?";
		
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
							boardDto.setWrittenDate(rs.getTimestamp("WRITTEN_DATE").toLocalDateTime());
							boardDto.setViews(rs.getInt("VIEWS"));
							boardDto.setType(rs.getInt("TYPE"));
							
							
							if(rs.getBoolean("DELETED_WRITER")) {
								boardDto.setWriterName("(알 수 없음)");
							} else {
								boardDto.setWriterName(rs.getString("NICKNAME"));	
							}
							
							return boardDto;
					}},boardId);
			return result;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	// DELETE
	
	/**
	 * boardId가 일치하는 게시글을 삭제하는 메서드
	 * 
	 * @param boardId
	 */
	
	public void delete(Long boardId) {
		String sql = "DELETE FROM BOARD WHERE BOARDID = ?";
		jdbcTemplate.update(sql, boardId);
	}
	
}