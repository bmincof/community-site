package service;

import org.springframework.beans.factory.annotation.Autowired;

import dao.UserDao;
import dto.LoginUserDto;
import entity.User;
import exception.UserNotFoundException;
import exception.WrongIdPasswordException;

public class UserLoginService {

	@Autowired
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public LoginUserDto login(String email, String password) {
		User user = userDao.selectByEmail(email);
		if (user == null) {
			throw new UserNotFoundException();
		}
			
		if(!user.getPassword().equals(password)) {
			throw new WrongIdPasswordException();
		}
		return new LoginUserDto(user.getUserId(), user.getEmail(), user.getNickname());
	}
	
}
