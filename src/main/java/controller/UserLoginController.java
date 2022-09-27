package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;

import dto.LoginRequestDto;
import dto.LoginUserDto;
import exception.UserNotFoundException;
import exception.WrongIdPasswordException;
import service.UserLoginService;

@Controller
@RequestMapping("/user")
public class UserLoginController {

	@Autowired
	private UserLoginService userLoginService;
	
	public void setUserLoginService( 
			UserLoginService userLoginService) {
		this.userLoginService = userLoginService;
	}
	
	@RequestMapping("/login")
	public String userLogin() {
		
		return "user/loginForm";
	}
	
	@RequestMapping("/loginPro")
	public String userLoginPro(@Valid LoginRequestDto loginReq, Errors errors, HttpServletRequest req, Model model) {
		try {
			HttpSession session = req.getSession();
		
			LoginUserDto loginUserInfo = userLoginService.userLogin(loginReq.getEmail(), loginReq.getPassword());
			session.setAttribute("loginUserInfo", loginUserInfo);
			return "redirect:/main";
		} catch (UserNotFoundException | WrongIdPasswordException e) {
			return "user/loginForm";
		}
		
	}
	
	@RequestMapping("/logout")
	public String userLogout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.invalidate();
		return "redirect:/main";
	}
	
}
