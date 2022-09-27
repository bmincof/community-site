package config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 웹 사이트 기능 작동에 필요한 스프링 빈 설정 클래스들
 * + 하위 설정 클래스 작동에 공통적으로 필요한 스프링 빈 설정 클래스들 (dataSource, jdbcTemplate)
 * 
 * @author a
 *
 */

@Configuration
@Import({UserConfig.class, BoardConfig.class})
public class Configs {

	
	/**
	 * JdbcTemplate 클래스가 DB에 접근할 수 있도록 하기 위한 DataSource 스프링 빈 객체
	 * 
	 * @return DataSource
	 */
	
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
	
	/**
	 * DAO 클래스가 쿼리문을 통해 데이터를 가져올 수 있도록 하기 위한 JdbcTemplate 스프링 빈 객체
	 * 
	 * @param dataSource
	 * @return JdbcTemplate
	 */
	
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
}
