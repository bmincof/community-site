package interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import vo.LoginUserVo;

/**
 * 로그인이 필요한 경로에 접근한 사용자의 로그인 상태를 확인하는 인터셉터
 * 로그인 되어있지 않을 경우 로그인 페이지로 이동시킨다. 
 * 
 * @author a
 *
 */

public class LoginCheckInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request,
							HttpServletResponse response,
							Object handler) throws Exception{
		LoginUserVo userInfo = (LoginUserVo) request.getSession().getAttribute("loginUserInfo");
		
		if(userInfo == null) {
			response.sendRedirect("/user/login");
			return false;
		} else {
			return true;}
	}
}