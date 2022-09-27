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

import entity.Reply;
import vo.ReplyVo;

public class ReplyDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<ReplyVo> replyMapper =
			new RowMapper<ReplyVo>() {
				@Override
				public ReplyVo mapRow(ResultSet rs, int rownum) throws SQLException{
					ReplyVo reply = new ReplyVo();
					reply.setReplyId(rs.getLong("REPLYID"));
					reply.setBoardId(rs.getLong("BOARDID")); 
					reply.setWriter(rs.getLong("WRITER"));
					reply.setWriterName(rs.getString("NICKNAME"));
					reply.setContent(rs.getString("CONTENT"));
					reply.setWrittenDate(rs.getTimestamp("WRITTEN_DATE").toLocalDateTime());
					return reply;
				}
	};
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void insert(Reply reply) {
		String sql = "insert into REPLY (BOARDID, WRITER, REF, CONTENT, WRITTEN_DATE) " +
				"values (?, ?, ?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
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
		}, keyHolder);
		Number keyValue = keyHolder.getKey();
		reply.setReplyId(keyValue.longValue());
	}
	
	public List<ReplyVo> selectListByBoardId(long boardId) {
		String sql = "SELECT R.*, U.NICKNAME "
				+ "FROM REPLY R "
				+ "LEFT OUTER JOIN USER U "
				+ "ON R.WRITER = U.USERID "
				+ "WHERE BOARDID = ?";
		try {
			List<ReplyVo> result = jdbcTemplate.query(sql, replyMapper, boardId);
			return result;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}	
	}
	
	public List<ReplyVo> selectListByRef(long ref) {
		String sql = "SELECT R.*, U.NICKNAME "
				+ "FROM REPLY R "
				+ "LEFT OUTER JOIN USER U "
				+ "ON R.WRITER = U.USERID "
				+ "WHERE REF = ?";
		try {
			List<ReplyVo> result = jdbcTemplate.query(sql, replyMapper, ref);
			return result;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}	
	}
	
	public List<ReplyVo> selectListByWriter(long writer) {
		String sql = "SELECT * FROM REPLY WHERE WRITER = ?";
		try {
			List<ReplyVo> result = jdbcTemplate.query(sql, replyMapper, writer);
			return result;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}	
	}
	
}
