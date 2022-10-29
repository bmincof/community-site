package service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import dao.UserDao;
import dto.UserRegisterRequest;
import entity.User;
import exception.DuplicateEmailException;
import exception.DuplicateNicknameException;
import exception.UserNotFoundException;

/**
 * 회원 가입, 탈퇴 기능을 제공하는 서비스
 * 
 * @author a
 *
 */

@Service
public class UserRegisterService {

	private final UserDao userDao;
	
	public UserRegisterService(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	/**
	 * 중복된 이메일이나 닉네임이 없을 때 새로운 회원정보를 DB에 추가하는 기능
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
								regReq.getIsAdmin(),
								false);
		
		userDao.insert(newUser);
	}
	
	public void delete(Long userId) {
		User user = userDao.selectById(userId);
		if (user == null) {
			throw new UserNotFoundException();
		}
		
		userDao.deleteUserInfo(userId);
	}
	
}