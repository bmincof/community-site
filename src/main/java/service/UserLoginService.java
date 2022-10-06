package service;

import org.springframework.beans.factory.annotation.Autowired;

import dao.UserDao;
import dto.LoginUserDto;
import entity.User;
import exception.UserNotFoundException;
import exception.WrongIdPasswordException;

/**
 * 로그인 기능을 제공하는 서비스
 * 
 * @author a
 *
 */

public class UserLoginService {

	@Autowired
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	/**
	 * DB에 입력받은 정보와 일치하는 계정이 존재할 경우, 해당 계정의 정보를 담은
	 * LoginUserDto를 반환한다.
	 * 1. 	DB에 입력받은 email과 일치하는 회원이 존재하는지 확인한다.
	 * 1-1. 일치하는 email이 없을 경우 UserNotFoundException을 발생
	 * 2. 	일치하는 email이 있을 경우, 해당 계정의 비밀번호와 입력받은 비밀번호가 일치하는 지 확인한다.
	 * 2-1. 비밀번호가 일치하지 않을 경우 WrongIdPasswordException을 발생
	 * 3.  	로그인 요청 정보와 일치하는 계정의 userId, email, nickname, isAdmin 정보를
	 * 		LoginUserDto 객체에 담아 반환한다.
	 * 
	 * @param email
	 * @param password
	 * @return
	 */
	
	public LoginUserDto userLogin(String email, String password) {
		User user = userDao.selectByEmail(email);
		
		if (user == null) {
			throw new UserNotFoundException();
		}
			
		if(!user.getPassword().equals(password)) {
			throw new WrongIdPasswordException();
		}
		return new LoginUserDto(user.getUserId(), 
								user.getEmail(),
								user.getNickname(),
								user.getIsAdmin());
	}
	
}
