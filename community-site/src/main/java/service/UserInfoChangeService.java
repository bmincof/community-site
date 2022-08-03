package service;

import org.springframework.beans.factory.annotation.Autowired;

import dao.UserDao;
import dto.UserDto;

public class UserInfoChangeService {

	@Autowired
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void changePassword(long userId, String newPwd) {
		UserDto user = userDao.selectById(userId);
		user.setPassword(newPwd);
		userDao.update(user);
	}
	
	public void changeInfo(long userId, String nickname, String phoneNumber) {
		UserDto user = userDao.selectById(userId);
		user.setNickname(nickname);
		user.setPhoneNumber(phoneNumber);
		userDao.update(user);
	}
	
}
