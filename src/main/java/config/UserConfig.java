package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dao.UserDao;
import service.UserInfoFindService;
import service.UserInfoChangeService;
import service.UserLoginService;
import service.UserRegisterService;

/**
 * 회원 정보 관련 기능 구현을 위한 스프링 빈 클래스
 * 
 * @author a
 *
 */

@Configuration
public class UserConfig {
	
	@Bean
	public UserDao userDao() {
		return new UserDao();
	}
	
	@Bean
	public UserInfoChangeService userInfoChangeService() {
		return new UserInfoChangeService();
	}
	
	@Bean
	public UserRegisterService userRegisterService() {
		return new UserRegisterService();
	}
	
	@Bean
	public UserLoginService userLoginService() {
		return new UserLoginService();
	}
	
	@Bean
	public UserInfoFindService userInfoFindService() {
		return new UserInfoFindService();
	}

	

	
}