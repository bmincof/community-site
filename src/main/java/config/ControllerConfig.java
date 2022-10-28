package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import controller.BoardController;
import controller.BoardDetailController;
import controller.BoardResponseController;
import controller.UserController;
import controller.UserInfoController;
import controller.UserLoginController;
import controller.UserRegisterController;
import service.BoardDetailService;

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
	
	@Bean
	public BoardDetailController boardDetailController() {
		return new BoardDetailController();
	}
	
	@Bean
	public BoardResponseController boardResponseController() {
		return new BoardResponseController();
	}
	
}