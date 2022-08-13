package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dao.UserDao;
import entity.User;
import exception.UserNotFoundException;
import exception.WrongUserInfoException;

public class UserFindInfoService {

	@Autowired
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	/* findEmailRequest 만들고 검증 후 넘기기 */
	public List<String> findEmail(String name, String phoneNumber) {
		List<User> users = userDao.selectByPhoneNumber(phoneNumber);
		List<String> result = new ArrayList<String>();
		
		if (users == null) {
			throw new UserNotFoundException();
		}
		
		for(User user : users) {
				if(!user.getName().equals(name)) {
					throw new WrongUserInfoException();
				}
				result.add(user.getEmail());
			}
			
			return result;
	}
	
	
	public String findPassword(String email, String name, String phoneNumber) {
		User user = userDao.selectByEmail(email);
		
		if (user == null) {
			throw new UserNotFoundException();
		}
		else if( !(user.getPhoneNumber().equals(phoneNumber) && user.getName().equals(name))) {
			throw new WrongUserInfoException();
		}

		return user.getPassword();
	}
	
	// 유저본인인증 코드부분 따로 빼내서 구현하기
}
