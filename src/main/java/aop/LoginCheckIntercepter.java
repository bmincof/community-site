package aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import vo.LoginUserVo;

public class LoginCheckIntercepter implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request,
							HttpServletResponse response,
							Object handler) throws Exception{
		LoginUserVo userInfo = (LoginUserVo) request.getSession().getAttribute("loginUserInfo");
		
		if(userInfo == null) {
			response.sendRedirect("user/login");
			return false;
		} else {
			return false;}
	}
}