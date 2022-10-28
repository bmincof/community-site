package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

import dto.BoardVotesDto;

/**
 * SQL쿼리문을 통해 BOARD_VOTES 테이블로부터 데이터를 받아오는 기능을 수행하는 클래스
 * 
 * @author a
 *
 */

public class BoardVotesDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<BoardVotesDto> bVotesMapper =
			new RowMapper<BoardVotesDto>() {
				@Override
				public BoardVotesDto mapRow(ResultSet rs, int rowNum)
				throws SQLException {
					BoardVotesDto boardVotes = new BoardVotesDto();
							boardVotes.setUp(rs.getInt("UP"));
							boardVotes.setDown(rs.getInt("DOWN"));
					return boardVotes;
				}
			};
			
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/**
	 * 일치하는 boardId를 가진 row들의 up 합계와 down 합계 정보를 갖는 BoardVotesDto 객체를 반환한다.
	 * 
	 * @param boardId
	 * @return
	 */
	
	public BoardVotesDto selectByBoardId(Long boardId) {
		String sql = "SELECT IFNULL(SUM(UP),0) AS UP, IFNULL(SUM(DOWN),0) AS DOWN FROM BOARD_VOTES WHERE BOARDID = ?";
		try {
			BoardVotesDto result = jdbcTemplate.queryForObject(sql,bVotesMapper,boardId);
			return result;
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
	}

	/**
	 * userId를 갖는 회원이 해당 게시글에서 투표한 내역을 반환한다. 
	 * 
	 * @param boardId
	 * @param userId
	 * @return
	 */
	
	public BoardVotesDto selectByIds(Long boardId, Long userId) {
		String sql = "SELECT * FROM BOARD_VOTES WHERE BOARDID = ? AND USERID = ?";
		try {
			BoardVotesDto result = jdbcTemplate.queryForObject(sql, bVotesMapper, boardId, userId);
			return result;
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	/**
	 * 해당 게시글에 userId를 갖는 회원의 추천 내역을 저장한다.
	 * 
	 * @param boardId
	 * @param userId
	 */
	
	public void insertUp(Long boardId, Long userId) {
		String sql = "INSERT INTO BOARD_VOTES (BOARDID, USERID, UP) "
				+ "VALUES (?, ?, ?)";
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
	
	/**
	 * 해당 게시글에 userId를 갖는 회원의 비추천 내역을 저장한다.
	 * 
	 * @param boardId
	 * @param userId
	 */
	
	public void insertDown(long boardId, long userId) {
		String sql = "INSERT INTO BOARD_VOTES (BOARDID, USERID, DOWN) "
				+ "VALUES (?, ?, ?)";
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
	
	/**
	 * 일치하는 boardId를 가진 데이터를 삭제한다.
	 * 게시글의 삭제에 사용되는 메서드
	 * 
	 * @param boardId
	 */
	
	public void deleteByBoardId(Long boardId) {
		String sql = "DELETE FROM BOARD_VOTES WHERE BOARDID = ?";
		jdbcTemplate.update(sql, boardId);
	}
	
	/**
	 * 일치하는 boardId, userId를 가진 데이터를 삭제한다.
	 * 추천/비추천 기능에 사용되는 메서드
	 * 
	 * @param boardId
	 * @param userId
	 */
	
	public void delete(long boardId, long userId) {
		String sql = "DELETE FROM BOARD_VOTES WHERE BOARDID = ? AND USERID = ?";
		jdbcTemplate.update(sql, boardId, userId);
	}
	
}