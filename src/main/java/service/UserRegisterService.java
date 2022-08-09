package service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import dao.UserDao;
import dto.UserRegisterRequest;
import entity.User;

public class UserRegisterService {

	@Autowired
	UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void regist(UserRegisterRequest regReq) {
		User user = userDao.selectByEmail(regReq.getEmail());
		
		if (user != null) {
			return;
		}
		
		User newUser = new User(regReq.getEmail(), regReq.getPassword(), regReq.getName(),
				regReq.getNickname(), regReq.getPhoneNumber(),regReq.getType(),LocalDateTime.now());
		
		userDao.insert(newUser);
	}
	
	public void delete(long userId) {
		userDao.deleteById(userId);
	}
	
}
