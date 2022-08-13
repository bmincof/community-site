package controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dto.LoginUserDto;
import dto.UserRegisterRequest;
import exception.DuplicateUserException;
import exception.UserNotFoundException;
import service.UserRegisterService;

@Controller
public class UserRegisterController {

	private UserRegisterService userRegisterService;
	
	public void setRegisterService(
			UserRegisterService userRegisterService) {
		this.userRegisterService = userRegisterService;
	}
	
	@RequestMapping("/user/register")
	public String registerForm() {
		return "user/registerForm";
	}
	
	@PostMapping("/user/registerPro")
	public String registerPro(@Valid UserRegisterRequest userRegisterReq) {
		
		try {
			userRegisterService.regist(userRegisterReq);
			return "redirect:/user/list";
		} catch (DuplicateUserException e) {
			return "user/registerForm";
		}
	}
	
	@RequestMapping("/user/withdraw")
	public String withdrawCheck() {
		return "user/withdrawCheck";
	}
	
	@PostMapping("/user/withdrawPro")
	public String withdrawPro(@RequestParam(value="check", defaultValue = "false") boolean check,
								HttpServletRequest req) {
		if(!check) {
			return "redirect:user/myPage";
		}
		else {
			try {
				LoginUserDto user = (LoginUserDto) req.getSession().getAttribute("loginUserInfo");
				long userId = user.getUserId();
				userRegisterService.delete(userId);
				return "redirect:/";
			} catch(UserNotFoundException e) {
				return "";
			}
		}
	}
	
}
