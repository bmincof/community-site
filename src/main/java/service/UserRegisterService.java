package service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import dao.UserDao;
import dto.UserRegisterRequest;
import entity.User;
import exception.DuplicateUserException;
import exception.UserNotFoundException;

public class UserRegisterService {

	@Autowired
	UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void regist(UserRegisterRequest regReq) {
		User user = userDao.selectByEmail(regReq.getEmail());
		
		if (user != null) {
			throw new DuplicateUserException();
		}
		
		User newUser = new User(regReq.getEmail(), 
								regReq.getPassword(),
								regReq.getName(),
								regReq.getNickname(),
								regReq.getPhoneNumber(),
								LocalDateTime.now(),
								regReq.getIsAdmin());
		
		userDao.insert(newUser);
	}
	
	public void delete(long userId) {
		User user = userDao.selectById(userId);
		if (user == null) {
			throw new UserNotFoundException();
		}
		userDao.delete(user);
	}
	
}
