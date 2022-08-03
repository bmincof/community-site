package service;

import org.springframework.beans.factory.annotation.Autowired;

import dao.UserDao;
import dto.LoginUserDto;
import dto.UserDto;

public class UserLoginService {

	@Autowired
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public LoginUserDto login(String email, String password) {
		UserDto user = userDao.selectByEmail(email);
		if (user == null || !user.getPassword().equals(password)) {
			return null;
		}
		return new LoginUserDto(user.getUserId(), user.getEmail(), user.getNickname());
	}
	
}
