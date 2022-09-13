package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

import dto.BoardVotesDto;

public class BoardVotesDao {

	private JdbcTemplate jdbcTemplate;
	private RowMapper<BoardVotesDto> bVotesMapper =
			new RowMapper<BoardVotesDto>() {
				@Override
				public BoardVotesDto mapRow(ResultSet rs, int rowNum)
				throws SQLException {
					BoardVotesDto boardVotes = new BoardVotesDto();
							boardVotes.setUp(rs.getInt("UP"));
							boardVotes.setDown(rs.getInt("DOWN"));
							//rs.getInt("BOOKMARK")
					return boardVotes;
				}
			};
			
	public BoardVotesDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public BoardVotesDto selectByBoardId(long boardId) {
		String sql = "select ifnull(sum(UP),0) as UP, ifnull(sum(DOWN),0) as DOWN from BOARD_VOTES where BOARDID=?";
		try {
			BoardVotesDto result = jdbcTemplate.queryForObject(sql,bVotesMapper,boardId);
			return result;
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
	}

	public BoardVotesDto selectByIds(long boardId, long userId) {
		String sql = "select * from BOARD_VOTES where BOARDID=? and USERID=?";
		try {
			BoardVotesDto result = jdbcTemplate.queryForObject(sql, bVotesMapper, boardId, userId);
			return result;
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
	}
	
//	public void updateUp(long boardId, long userId) {
//		String sql = "update BOARD_VOTES set UP=1 where BOARDID=? and USERID=?";
//		Object[] params = {boardId,userId};
//		jdbcTemplate.update(sql,params);
//	}
//	
//	public void updateDown(long boardId, long userId) {
//		String sql = "update BOARD_VOTES set DOWN=1 where BOARDID=? and USERID=?";
//		Object[] params = {boardId,userId};
//		jdbcTemplate.update(sql,params);
//	}
	
	public void insertUp(long boardId, long userId) {
		String sql = "insert into BOARD_VOTES (BOARDID, USERID, UP) "
				+ "values (?, ?, ?)";
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setLong(1, boardId);
				pstmt.setLong(2, userId);
				pstmt.setInt(3, 1);
				return pstmt;
			}
		});
	}
	
	public void insertDown(long boardId, long userId) {
		String sql = "insert into BOARD_VOTES (BOARDID, USERID, DOWN) "
				+ "values (?, ?, ?)";
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pstmt = conn.prepareStatement(sql,1);
				pstmt.setLong(1, boardId);
				pstmt.setLong(2, userId);
				pstmt.setInt(3, 1);
				return pstmt;
			}
		});
	}
	
	public void delete(long boardId, long userId) {
		String sql = "delete from BOARD_VOTES where BOARDID=? and USERID=?";
		jdbcTemplate.update(sql, boardId, userId);
	}
	
	public int countUp(long boardId) {
		String sql = "count(UP) from BOARD_VOTES where BOARDID=?";
		try {
			return jdbcTemplate.queryForObject(sql, Integer.class, boardId);
		} catch(EmptyResultDataAccessException e) {
			return 0;
		}
	}
	
	public int countDown(long boardId) {
		String sql = "count(DOWN) from BOARD_VOTES where BOARDID=?";
		try {
			return jdbcTemplate.queryForObject(sql, Integer.class, boardId);
		} catch(EmptyResultDataAccessException e) {
			return 0;
		}
	}
	
}
