package interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import vo.LoginUserVo;

/**
 * 관리자 권한이 필요한 경로로 접근한 사용자의 관리자 여부를 파악하는 인터셉터
 * 로그인하지 않았거나 로그인 계정이 관리자가 아닐 경우 메인페이지로 보낸다.
 * 
 * @author a
 *
 */

public class AdminCheckInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request,
							HttpServletResponse response,
							Object handler) throws Exception{
		LoginUserVo userInfo = (LoginUserVo) request.getSession().getAttribute("loginUserInfo");
		
		if(userInfo == null || !userInfo.getIsAdmin()) {
			response.sendRedirect("/main");
			return false;
		} else {
			return true;}
	}
	
}