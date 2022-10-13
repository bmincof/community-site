package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import controller.BoardController;
import controller.UserController;
import controller.UserInfoController;
import controller.UserLoginController;
import controller.UserRegisterController;

@Configuration
public class ControllerConfig {
	
	@Bean
	public UserController userController() {
		return new UserController();
	}
		
	@Bean
	public UserRegisterController userRegisterController() {
		return new UserRegisterController();
	}
	
	@Bean
	public UserLoginController userLoginController() {
		return new UserLoginController();
	}
	
	@Bean
	public UserInfoController userInfoController() {
		return new UserInfoController();
	}
	
	@Bean
	public BoardController boardController() {
		return new BoardController();
	}
	
}