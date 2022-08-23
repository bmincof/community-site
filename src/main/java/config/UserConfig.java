package config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dao.BoardDao;
import dao.UserDao;
import service.BoardService;
import service.UserFindInfoService;
import service.UserInfoChangeService;
import service.UserLoginService;
import service.UserRegisterService;

@Configuration
public class UserConfig {

	@Bean
	public DataSource userDataSource() {
		DataSource ds = new DataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/study1?useSSL=false");
		ds.setUsername("test");
		ds.setPassword("test");
		ds.setInitialSize(2);
		ds.setMaxActive(10);
		ds.setMaxIdle(10); // ??
		ds.setTestWhileIdle(true);
		ds.setMinEvictableIdleTimeMillis(60000*3);
		ds.setTimeBetweenEvictionRunsMillis(10*1000);
		return ds;
	}
	
	@Bean
	public UserDao userDao() {
		return new UserDao(userDataSource());
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
	public UserFindInfoService userFindInfoService() {
		return new UserFindInfoService();
	}
	
	@Bean
	public BoardDao boardDao() {
		return new BoardDao(userDataSource());
	}
	
	@Bean
	public BoardService boardService() {
		return new BoardService();
	}
	
}
