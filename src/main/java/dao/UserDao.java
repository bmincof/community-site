package dao;

import java.sql.Timestamp;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import entity.User;

/**
 * SQL쿼리문을 통해 User 테이블로부터 데이터를 받아오는 기능을 수행하는 클래스
 * 
 * @author a
 *
 *
 */

@Repository
public class UserDao {
	
	private final JdbcTemplate jdbcTemplate;
	
	public UserDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private RowMapper<User> userMapper = new RowMapper<User>() {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User(
					rs.getString("EMAIL"),
					rs.getString("PASSWORD"),
					rs.getString("NAME"),
					rs.getString("NICKNAME"),
					rs.getString("PHONENUMBER"),
					rs.getTimestamp("REGDATE").toLocalDateTime(),
					rs.getBoolean("IS_ADMIN"),
					rs.getBoolean("IS_DELETED")
					);
			user.setUserId(rs.getLong("USERID"));
			return user;
		}
	};
	
	// CREATE
	
	public void insert(User user) {
		String sql = "INSERT INTO USER (EMAIL, PASSWORD, NAME, NICKNAME, PHONENUMBER, REGDATE, IS_ADMIN) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, user.getEmail());
				pstmt.setString(2, user.getPassword());
				pstmt.setString(3, user.getName());
				pstmt.setString(4, user.getNickname());
				pstmt.setString(5, user.getPhoneNumber());
				pstmt.setTimestamp(6, Timestamp.valueOf(user.getRegisterDate()));
				pstmt.setBoolean(7, user.getIsAdmin());
				return pstmt;
			}
		});
		
	}
	
	// UPDATE
	
	public void update(User user) {
		String sql = "UPDATE USER SET PASSWORD = ?, NAME = ?, NICKNAME = ?, PHONENUMBER = ? WHERE EMAIL = ?";
		Object[] params = {user.getPassword(), user.getName(), user.getNickname(), user.getPhoneNumber(), user.getEmail()};
		jdbcTemplate.update(sql,params);
	}
	
	// READ
	
	public User selectById(Long userId) {
		String sql = "SELECT * FROM USER WHERE USERID = ?";
		try {
			User result = jdbcTemplate.queryForObject(sql, userMapper, userId);
			return result;
		} catch (EmptyResultDataAccessException ex) {
			return null;
		}
	}
	
	public User selectByEmail(String email) {
		String sql = "SELECT * FROM USER WHERE EMAIL = ?";
		try {
			User result = jdbcTemplate.queryForObject(sql, userMapper, email);
			return result;
		} catch (EmptyResultDataAccessException ex) {
			return null;
		}
	}
	
	public User selectByNickname(String nickname) {
		String sql = "SELECT * FROM USER WHERE NICKNAME = ?";
		try {
			User result = jdbcTemplate.queryForObject(sql, userMapper, nickname);
			return result;
		} catch (EmptyResultDataAccessException ex) {
			return null;
		}
	}
	
	public List<User> selectByName(String name) {
		String sql = "SELECT * FROM USER WHERE NAME = ?";
		try {
			List<User> result = jdbcTemplate.query(sql, userMapper, name);
			return result;
		} catch (EmptyResultDataAccessException ex) {
			return null;
		}
	}
	
	public List<User> selectAll() {
		String sql = "SELECT * FROM USER";
		List<User> results = jdbcTemplate.query(sql, userMapper);
		return results;
	}
	
	// DELETE
	
	public void deleteUserInfo(Long userId) {
		String sql = "UPDATE USER SET EMAIL = 'deleted"+userId+"', PASSWORD = '', NAME = '', NICKNAME = 'deleted"+userId+"', "
				+ "PHONENUMBER = '', IS_DELETED = '1' WHERE USERID = ?";
		jdbcTemplate.update(sql, userId);
	}
	
}