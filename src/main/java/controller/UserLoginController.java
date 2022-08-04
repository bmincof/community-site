package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.validator.cfg.context.ReturnValueConstraintMappingContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dto.LoginRequestDto;
import dto.LoginUserDto;
import service.UserLoginService;

@Controller
public class UserLoginController {

	private UserLoginService userLoginService;
	
	public void setUserLoginService( 
			UserLoginService userLoginService) {
		this.userLoginService = userLoginService;
	}
	
	@RequestMapping("/user/login")
	public String userLogin() {
		
		return "user/loginForm";
	}
	
	@RequestMapping("/user/loginPro")
	public String userLoginPro(LoginRequestDto loginReq ,HttpServletRequest req, Model model) {
		
		HttpSession session = req.getSession();
	
		LoginUserDto loginUserInfo = userLoginService.login(loginReq.getEmail(), loginReq.getPassword());
		
		if (loginUserInfo != null) {
			session.setAttribute("loginUserInfo", loginUserInfo);
			return "redirect:/";
		}
		else {
			return "user/loginForm";
		}
		
	}
	
	@RequestMapping("/user/logout")
	public String userLogout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.invalidate();
		return "redirect:/";
	}
	
	
}
