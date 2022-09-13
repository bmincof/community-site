package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import controller.BoardController;
import controller.UserController;
import controller.UserFindInfoController;
import controller.UserInfoChangeController;
import controller.UserLoginController;
import controller.UserRegisterController;
import dao.BoardDao;
import dao.BoardVotesDao;
import dao.UserDao;
import service.BoardService;
import service.UserFindInfoService;
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
	@Autowired
	private UserFindInfoService userFindInfoService;
	@Autowired
	private BoardService boardService;
	@Autowired
	private BoardVotesDao boardVotesDao;
	
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
	
	@Bean
	public UserFindInfoController userFindInfoController() {
		UserFindInfoController controller = new UserFindInfoController();
		controller.setUserFindInfoService(userFindInfoService);
		return controller;
	}
	
	@Bean
	public BoardController boardController() {
		BoardController controller = new BoardController();
		controller.setBoardService(boardService);
		controller.setBoardVotesDao(boardVotesDao);
		return controller;
	}
	
}
