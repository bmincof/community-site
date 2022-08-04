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

import dto.UserDto;

public class UserDao {
	
	private JdbcTemplate jdbcTemplate;
	private RowMapper<UserDto> userMapper =
		new RowMapper<UserDto>() {
			@Override
			public UserDto mapRow(ResultSet rs, int rowNum)
				throws SQLException {
				UserDto user = new UserDto();
				
				user.setUserId(rs.getLong("USERID"));
				user.setEmail(rs.getString("EMAIL"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setName(rs.getString("NAME"));
				user.setNickname(rs.getString("NICKNAME"));
				user.setPhoneNumber(rs.getString("PHONENUMBER"));
				user.setRegisterDate(rs.getTimestamp("REGDATE").toLocalDateTime());
				return user;
			}
		};
	
	public UserDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	// CREATE
	
	public void insert(UserDto user) {
		String sql = "insert into USER (EMAIL, PASSWORD, NAME, NICKNAME, PHONENUMBER, REGDATE) " +
				"values (?, ?, ?, ?, ?, ?)";
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
				return pstmt;
			}
		}, keyHolder);
		Number keyValue = keyHolder.getKey();
		user.setUserId(keyValue.longValue());
		
	}
	
	// UPDATE
	
	public void update(UserDto user) {
		String sql = "update USER set PASSWORD = ?, NAME = ?, NICKNAME = ?, PHONENUMBER = ? where EMAIL = ?";
		Object[] params = {user.getPassword(), user.getName(), user.getNickname(), user.getPhoneNumber(), user.getEmail()};
		jdbcTemplate.update(sql,params);
	}
	
	// READ
	
	public UserDto selectById(long userId) {
		String sql = "select * from USER where USERID = ?";
		UserDto result = jdbcTemplate.queryForObject(sql, userMapper, userId);
		return result == null ? null : result;
	}
	
	public UserDto selectByEmail(String email) {
		String sql = "select * from USER where EMAIL = ?";
		try {
			UserDto result = jdbcTemplate.queryForObject(sql, userMapper, email);
			return result;
		} catch (EmptyResultDataAccessException ex) {
			return null;
		}
	}
	
	public UserDto selectByPhoneNumber(String phoneNumber) {
		String sql = "select * from USER where PHONENUMBER = ?";
		UserDto result = jdbcTemplate.queryForObject(sql, userMapper, phoneNumber);
		return result == null ? null : result;
	}
	
	public List<UserDto> selectAll() {
		String sql = "select * from USER";
		List<UserDto> results = jdbcTemplate.query(sql, userMapper);
		return results;
	}
	
	// DELETE
	
	public void deleteById(long userId) {
		String sql = "delete from USER where USERID = ?";
		jdbcTemplate.update(sql, userId);
	}
	
}
