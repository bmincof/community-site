package service;

import org.springframework.beans.factory.annotation.Autowired;

import dao.UserDao;
import dto.UserDto;
import entity.User;

public class UserInfoChangeService {

	@Autowired
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	/* 세션 + User 변경 -> 로직 다시 구현하기 */
	
	public void changePassword(long userId, String newPwd) {
//		User old = userDao.selectById(userId);
//		User changed = new User(old.) 
//		userDao.update(user);
	}
	
	public void changeNickname(long userId, String nickname) {
//		User user = UserDao.selectById(userId);
//		user.setNickname(nickname);
//		userDao.update(user);
	}
	
	public void changePhoneNumber(long userId, String phoneNumber) {
		
	}
	
}
