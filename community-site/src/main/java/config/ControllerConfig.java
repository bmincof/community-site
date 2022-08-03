package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import controller.UserController;
import controller.UserInfoChangeController;
import controller.UserLoginController;
import controller.UserRegisterController;
import dao.UserDao;
import service.UserInfoChangeService;
import service.UserLoginService;
import service.UserRegisterService;

@Configuration
public class ControllerConfig {

	@Autowired
	private UserDao userDao;
	@Autowired
	private UserInfoChangeService userInfoChangeService;
	@Autowired 
	private UserRegisterService userRegisterService;
	@Autowired
	private UserLoginService userLoginService;
	
	@Bean
	public UserController userController() {
		UserController controller = new UserController();
		controller.setUserDao(userDao);
		return controller;
	}
	
	@Bean
	public UserInfoChangeController userInfoChangeController() {
		UserInfoChangeController controller = new UserInfoChangeController();
		controller.setInfoChangeService(userInfoChangeService);
		return controller;
	}
	
	@Bean
	public UserRegisterController userRegisterController() {
		UserRegisterController controller = new UserRegisterController();
		controller.setRegisterService(userRegisterService);
		return controller;
	}
	
	@Bean
	public UserLoginController userLoginController() {
		UserLoginController controller = new UserLoginController();
		controller.setUserLoginService(userLoginService);
		return controller;
	}
	
}
