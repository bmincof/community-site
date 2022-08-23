package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import entity.Board;
import entity.User;

public class BoardDao {
	
	public BoardDao(DataSource boardDataSource) {
		this.jdbcTemplate = new JdbcTemplate(boardDataSource);
	}
	
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
						rs.getTimestamp("WRITTEN_DATE").toLocalDateTime());
				board.setBoardId(rs.getLong("BOARDID"));
				return board;
			}
		};
	
	// CREATE
	
	public void insert(Board board) {
		String sql = "insert into USER (TITLE, CONTENT, WRITER ,WRITTNE_DATE, VIEWS, UP_VOTES, DOWN_VOTES) " +
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
				pstmt.setInt(6, board.getUpVotes());
				pstmt.setInt(7, board.getDownVotes());
				return pstmt;
			}
		}, keyHolder);
		Number keyValue = keyHolder.getKey();
		board.setBoardId(keyValue.longValue());
		
	}
//	
//	// UPDATE
//	
//	public void update(User user) {
//		String sql = "update USER set PASSWORD = ?, NAME = ?, NICKNAME = ?, PHONENUMBER = ? where EMAIL = ?";
//		Object[] params = {user.getPassword(), user.getName(), user.getNickname(), user.getPhoneNumber(), user.getEmail()};
//		jdbcTemplate.update(sql,params);
//	}
//	
//	// READ
//	
//	public User selectById(long userId) {
//		String sql = "select * from USER where USERID = ?";
//		try {
//			User result = jdbcTemplate.queryForObject(sql, userMapper, userId);
//			return result;
//		} catch (EmptyResultDataAccessException ex) {
//			return null;
//		}
//	}
//	
//	public User selectByEmail(String email) {
//		String sql = "select * from USER where EMAIL = ?";
//		try {
//			User result = jdbcTemplate.queryForObject(sql, userMapper, email);
//			return result;
//		} catch (EmptyResultDataAccessException ex) {
//			return null;
//		}
//	}
//	
//	public List<User> selectByPhoneNumber(String phoneNumber) {
//		String sql = "select * from USER where PHONENUMBER = ?";
//		try {
//			List<User> result = jdbcTemplate.query(sql, userMapper, phoneNumber);
//			return result;
//		} catch (EmptyResultDataAccessException ex) {
//			return null;
//		}
//	}
	
	public List<Board> selectAll() {
		String sql = "select * from BOARD";
		List<Board> results = jdbcTemplate.query(sql, boardMapper);
		return results;
	}
	
	// DELETE
//	
//	public void delete(User user) {
//		String sql = "delete from USER where USERID = ?";
//		jdbcTemplate.update(sql, user.getUserId());
//	}
//	
}
