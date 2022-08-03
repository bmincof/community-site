package config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dao.UserDao;
import service.UserInfoChangeService;
import service.UserLoginService;
import service.UserRegisterService;

@Configuration
public class UserConfig {

	@Bean
	public DataSource dataSource() {
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
		return new UserDao(dataSource());
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
	
	
}
