package dao;

import java.sql.Timestamp;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import entity.User;

public class UserDao {
	
	public UserDao(DataSource userDataSource) {
		this.jdbcTemplate = new JdbcTemplate(userDataSource);
	}
	
	private JdbcTemplate jdbcTemplate;
	private RowMapper<User> userMapper =
		new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum)
				throws SQLException {
				User user = new User(
						rs.getString("EMAIL"),
						rs.getString("PASSWORD"),
						rs.getString("NAME"),
						rs.getString("NICKNAME"),
						rs.getString("PHONENUMBER"),
						rs.getTimestamp("REGDATE").toLocalDateTime(),
						rs.getBoolean("IS_ADMIN")
						);
				user.setUserId(rs.getLong("USERID"));
				return user;
			}
		};
	
	// CREATE
	
	public void insert(User user) {
		String sql = "insert into USER (EMAIL, PASSWORD, NAME, NICKNAME, PHONENUMBER, REGDATE, IS_ADMIN) " +
				"values (?, ?, ?, ?, ?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"USERID"});
				pstmt.setString(1, user.getEmail());
				pstmt.setString(2, user.getPassword());
				pstmt.setString(3, user.getName());
				pstmt.setString(4, user.getNickname());
				pstmt.setString(5, user.getPhoneNumber());
				pstmt.setTimestamp(6, Timestamp.valueOf(user.getRegisterDate()));
				pstmt.setBoolean(7, user.getIsAdmin());
				return pstmt;
			}
		}, keyHolder);
		Number keyValue = keyHolder.getKey();
		user.setUserId(keyValue.longValue());
		
	}
	
	// UPDATE
	
	public void update(User user) {
		String sql = "update USER set PASSWORD = ?, NAME = ?, NICKNAME = ?, PHONENUMBER = ? where EMAIL = ?";
		Object[] params = {user.getPassword(), user.getName(), user.getNickname(), user.getPhoneNumber(), user.getEmail()};
		jdbcTemplate.update(sql,params);
	}
	
	// READ
	
	public User selectById(long userId) {
		String sql = "select * from USER where USERID = ?";
		try {
			User result = jdbcTemplate.queryForObject(sql, userMapper, userId);
			return result;
		} catch (EmptyResultDataAccessException ex) {
			return null;
		}
	}
	
	public User selectByEmail(String email) {
		String sql = "select * from USER where EMAIL = ?";
		try {
			User result = jdbcTemplate.queryForObject(sql, userMapper, email);
			return result;
		} catch (EmptyResultDataAccessException ex) {
			return null;
		}
	}
	
	public List<User> selectByPhoneNumber(String phoneNumber) {
		String sql = "select * from USER where PHONENUMBER = ?";
		try {
			List<User> result = jdbcTemplate.query(sql, userMapper, phoneNumber);
			return result;
		} catch (EmptyResultDataAccessException ex) {
			return null;
		}
	}
	
	public List<User> selectAll() {
		String sql = "select * from USER";
		List<User> results = jdbcTemplate.query(sql, userMapper);
		return results;
	}
	
	// DELETE
	
	public void delete(User user) {
		String sql = "delete from USER where USERID = ?";
		jdbcTemplate.update(sql, user.getUserId());
	}
	
}
