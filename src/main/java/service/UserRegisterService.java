package service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import dao.UserDao;
import dto.UserRegisterRequest;
import entity.User;
import exception.DuplicateEmailException;
import exception.DuplicateNicknameException;
import exception.UserNotFoundException;

/**
 * 회원 가입, 탈퇴 기능을 제공하는 서비스 클래스
 * 
 * @author a
 *
 */

public class UserRegisterService {

	@Autowired
	UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	/**
	 * 중복된 이메일이나 닉네임이 없다면, 새로운 회원정보를 DB에 추가하는 기능
	 * 
	 * @param regReq
	 */
	
	public void regist(UserRegisterRequest regReq) {
		
		if (userDao.selectByEmail(regReq.getEmail()) != null) {
			throw new DuplicateEmailException();
		}
		
		if (userDao.selectByNickname(regReq.getNickname()) != null) {
			throw new DuplicateNicknameException();
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
