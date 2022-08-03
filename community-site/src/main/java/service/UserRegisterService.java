package service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import dao.UserDao;
import dto.UserDto;
import dto.UserRegisterDto;

public class UserRegisterService {

	@Autowired
	UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void regist(UserRegisterDto regReq) {
		UserDto user = userDao.selectByEmail(regReq.getEmail());
		
		if (user != null) {
			return;
		}
		
		UserDto newUser = new UserDto();
		newUser.setEmail(regReq.getEmail());
		newUser.setPassword(regReq.getPassword());
		newUser.setName(regReq.getName());
		newUser.setNickname(regReq.getNickname());
		newUser.setPhoneNumber(regReq.getPhoneNumber());
		newUser.setRegisterDate(LocalDateTime.now());
		
		userDao.insert(newUser);
	}
	
	public void delete(long userId) {
		userDao.deleteById(userId);
	}
	
}
