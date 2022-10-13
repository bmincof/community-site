package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dao.UserDao;
import dto.UserInfoFindRequest;
import entity.User;
import exception.UserNotFoundException;
import exception.WrongUserInfoException;

/**
 * 회원 정보 찾기 기능을 제공하는 서비스
 * 
 * @author a
 *
 */

public class UserInfoFindService {

	@Autowired
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	/**
	 * 입력받은 회원 정보와 일치하는 이메일 목록을 반환한다.
	 * 
	 * @param req
	 * @return
	 */
	
	public List<String> findEmail(UserInfoFindRequest req) {
		List<User> users = userDao.selectByName(req.getName());
		List<String> result = new ArrayList<String>();
		
		if(users != null) {
			for(User user : users) {
				if(user.getPhoneNumber().equals(req.getPhoneNumber())) {
					result.add(user.getEmail());
				}
			}
		}
		
		if (result.isEmpty()) {
			throw new UserNotFoundException();
		}
			
		return result;
	}
	
	/**
	 * 입력받은 회원 정보와 일치하는 이메일이 있을 경우 비밀번호 변경 로직 수행에 필요한 userId 반환
	 * 
	 * @param req
	 * @return
	 */
	
	public Long findPassword(UserInfoFindRequest req) {
		User user = userDao.selectByEmail(req.getEmail());
		
		if (user == null) {
			throw new UserNotFoundException();
		}
		else if(!(user.getPhoneNumber().equals(req.getPhoneNumber()) && user.getName().equals(req.getName()))) {
			throw new WrongUserInfoException();
		}

		return user.getUserId();
	}
	
}
