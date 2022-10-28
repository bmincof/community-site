package config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import interceptor.AdminCheckInterceptor;
import interceptor.LoginCheckInterceptor;

/**
 * 서블릿, 스프링 기본 제공 API 관련 스프링 빈 설정 클래스
 * 
 * @author a
 *
 */

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer{
	
	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/view/",".jsp");
	}
	
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
		ms.setBasenames("message.label");
		ms.setDefaultEncoding("UTF-8");
		return ms;
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/main").setViewName("main");
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {		
		registry.addInterceptor(new LoginCheckInterceptor())
				.addPathPatterns("/**")
				.excludePathPatterns("/main","/user/login","/user/loginDo","/board/list","/board/list/*",
						"/board/detail/*","/user/findEmail","/user/findPwd",
						"/user/register","/user/registerDo","/admin/*");
		registry.addInterceptor(new AdminCheckInterceptor())
				.addPathPatterns("/admin/*");
	}
	
}