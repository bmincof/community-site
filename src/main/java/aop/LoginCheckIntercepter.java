package aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import dto.LoginUserDto;

public class LoginCheckIntercepter implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request,
							HttpServletResponse response,
							Object handler) throws Exception{
		LoginUserDto userInfo = (LoginUserDto) request.getSession().getAttribute("loginUserInfo");
		
		if(userInfo == null) {
			response.sendRedirect("user/login");
			return false;
		} else {
			return false;}
	}
}
