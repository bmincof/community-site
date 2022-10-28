package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dao.UserDao;
import dto.UserInfoChangeRequest;
import entity.User;
import exception.DuplicateNicknameException;
import exception.UserNotFoundException;

/**
 * 회원정보를 변경하기 위한 서비스
 * 
 */

public class UserInfoChangeService {

	@Autowired
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	/**
	 * 입력받은 정보와 일치하는 회원의 비밀번호를 새로운 비밀번호로 변경하는 메서드
	 * 
	 * @param req
	 */
	
	public List<User> showUserList() {
		return userDao.selectAll();
	}
	
	public void changePassword(UserInfoChangeRequest req) {
		User user = userDao.selectById(req.getUserId());
		if (user == null) {
			throw new UserNotFoundException();
		}
		
		user.changePassword(req.getNewPassword());
		userDao.update(user);
	}
	
	/**
	 * 입력받은 정보와 일치하는 회원의 닉네임을 새로운 닉네임으로 변경하는 메서드
	 * 
	 * @param req
	 */
	
	public void changeNickname(UserInfoChangeRequest req) {
		if(userDao.selectByNickname(req.getNewNickname()) != null ) {
			throw new DuplicateNicknameException();
		}
			
		User user = userDao.selectById(req.getUserId());
		
		if(user == null) {
			throw new UserNotFoundException();
		}
		
		user.changeNickname(req.getNewNickname());
		userDao.update(user);
	}
	
	/**
	 * 입력받은 정보와 일치하는 회원의 휴대전화번호를 새로운 휴대전화번호로 변경하는 메서드
	 * 
	 * @param req
	 */
	
	public void changePhoneNumber(UserInfoChangeRequest req) {
		User user = userDao.selectById(req.getUserId());
		if (user == null) {
			throw new UserNotFoundException();
		}
		user.changePhoneNumber(req.getNewPhoneNumber());
		userDao.update(user);
	}
	
}