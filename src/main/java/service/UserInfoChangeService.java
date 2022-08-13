package service;

import org.springframework.beans.factory.annotation.Autowired;

import dao.UserDao;
import dto.UserDto;
import entity.User;
import exception.UserNotFoundException;

public class UserInfoChangeService {

	@Autowired
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	/* 세션 + User 변경 -> 로직 다시 구현하기 
	 * user == null 이면 throw exception 하는 공통기능 구현?
	 * */
	
	public void changePassword(long userId, String newPwd) {
		User user = userDao.selectById(userId);
		if (user == null) {
			throw new UserNotFoundException();
		}
		
		user.changePassword(newPwd);
		userDao.update(user);
	}
	
	public void changeNickname(long userId, String newNickname) {
		User user = userDao.selectById(userId);
		if(user == null) {
			throw new UserNotFoundException();
		}
		user.changeNickname(newNickname);
		userDao.update(user);
	}
	
	public void changePhoneNumber(long userId, String newPhoneNumber) {
		User user = userDao.selectById(userId);
		if (user == null) {
			throw new UserNotFoundException();
		}
		user.changePhoneNumber(newPhoneNumber);
		userDao.update(user);
	}
	
}
