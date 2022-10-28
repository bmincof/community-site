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

import dto.ReplyDto;
import entity.Reply;

/**
 * SQL쿼리문을 통해 REPLY 테이블로부터 데이터를 받아오는 기능을 수행하는 클래스
 * 
 * @author a
 *
 */

public class ReplyDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<ReplyDto> replyMapper =
			new RowMapper<ReplyDto>() {
				@Override
				public ReplyDto mapRow(ResultSet rs, int rownum) throws SQLException{
					ReplyDto reply = new ReplyDto();
					reply.setReplyId(rs.getLong("REPLYID"));
					reply.setBoardId(rs.getLong("BOARDID")); 
					reply.setWriter(rs.getLong("WRITER"));
					reply.setContent(rs.getString("CONTENT"));
					reply.setWrittenDate(rs.getTimestamp("WRITTEN_DATE").toLocalDateTime());
					reply.setIsDeleted(rs.getBoolean("IS_DELETED"));
					
					if(rs.getBoolean("DELETED_WRITER")) {
						reply.setWriterName("(알 수 없음)");
					} else {
						reply.setWriterName(rs.getString("NICKNAME"));	
					}
					
					return reply;
				}
	};
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void insert(Reply reply) {
		String sql = "INSERT INTO REPLY (BOARDID, WRITER, REF, CONTENT, WRITTEN_DATE) " +
				"VALUES (?, ?, ?, ?, ?)";
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"REPLYID"});
				pstmt.setLong(1, reply.getBoardId());
				pstmt.setLong(2, reply.getWriter());
				pstmt.setLong(3, reply.getRef());
				pstmt.setString(4, reply.getContent());
				pstmt.setTimestamp(5, Timestamp.valueOf(reply.getWrittenDate()));
				return pstmt;
			}
		});
	}
	
	/**
	 * 일치하는 boardId를 갖는 댓글들을 모두 반환한다.
	 * 
	 * @param boardId
	 * @return
	 */
	
	public List<ReplyDto> selectListByBoardId(Long boardId) {
		String sql = "SELECT R.*, U.NICKNAME, U.IS_DELETED AS DELETED_WRITER "
				+ "FROM REPLY R "
				+ "LEFT OUTER JOIN USER U "
				+ "ON R.WRITER = U.USERID "
				+ "WHERE BOARDID = ? AND REF = 0";
		try {
			List<ReplyDto> result = jdbcTemplate.query(sql, replyMapper, boardId);
			return result;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}	
	}
	
	/**
	 * 일치하는 ref를 갖는 댓글들을 모두 반환한다.
	 * 댓글에 대한 답글을 불러올 때 사용되는 메서드
	 * 
	 * @param ref
	 * @return
	 */
	
	public List<ReplyDto> selectListByRef(Long ref) {
		String sql = "SELECT R.*, U.NICKNAME, U.IS_DELETED AS DELETED_WRITER "
				+ "FROM REPLY R "
				+ "LEFT OUTER JOIN USER U "
				+ "ON R.WRITER = U.USERID "
				+ "WHERE REF = ?";
		try {
			List<ReplyDto> result = jdbcTemplate.query(sql, replyMapper, ref);
			return result;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}	
	}
	
	/**
	 * 일치하는 replyId를 갖는 댓글을 반환한다.
	 * 
	 * @param replyId
	 * @return
	 */
	
	public ReplyDto selectByReplyId(Long replyId) {
		String sql = "SELECT R.*, U.NICKNAME, U.IS_DELETED AS DELETED_WRITER "
				+ "FROM REPLY R "
				+ "LEFT OUTER JOIN USER U "
				+ "ON R.WRITER = U.USERID "
				+ "WHERE REPLYID = ?";
		try {
			ReplyDto result = jdbcTemplate.queryForObject(sql, replyMapper, replyId);
			return result;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}	
	}
	
	/**
	 * 일치하는 replyId를 갖는 댓글의 내용을 제거하고 IS_DELETED를 true로 바꾼다.
	 * 댓글 작성자가 댓글을 삭제할 때 사용되는 메서드
	 * 
	 * @param replyId
	 */
	
	public void deleteContent(Long replyId) {
		String sql = "UPDATE REPLY SET CONTENT = '', IS_DELETED = 1 WHERE REPLYID = ?";
		jdbcTemplate.update(sql, replyId);
	}
	
	/**
	 * 일치하는 boardId를 갖는 댓글을 모두 삭제한다.
	 * 게시글을 삭제할 때 사용되는 메서드
	 * 
	 * @param boardId
	 */
	
	public void deleteByBoardId(Long boardId) {
		String sql = "DELETE FROM REPLY WHERE BOARDID = ?";
		jdbcTemplate.update(sql, boardId);
	}
	
	/**
	 * 일치하는 replyId를 갖는 댓글의 내용을 newContent로 수정한다.
	 * 
	 * @param replyId
	 * @param newContent
	 */
	
	public void changeContent(Long replyId, String newContent) {
		String sql = "UPDATE REPLY SET CONTENT = ? WHERE REPLYID = ?";
		jdbcTemplate.update(sql, newContent, replyId);
	}
	
}