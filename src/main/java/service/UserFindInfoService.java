package service;

import dao.UserDao;
import dto.UserDto;

public class UserFindInfoService {

	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public String FindEmail(String name, String phoneNumber) {
		UserDto user = userDao.selectByPhoneNumber(phoneNumber);
		if (user != null && user.getName().equals(name)) {
			return user.getEmail();
		} else {
			return null;
		}
	}
	
	public String FindPassword(String email, String name, String phoneNumber) {
		UserDto user = userDao.selectByEmail(email);
		if (user != null && user.getPhoneNumber().equals(phoneNumber) && user.getName().equals(name)) {
			return user.getPassword();
		} else {
			return null;
		}
	}
	
	// 유저본인인증 코드부분 따로 빼내서 구현하기
}
