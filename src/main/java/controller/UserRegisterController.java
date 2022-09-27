package controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dto.LoginUserDto;
import dto.UserRegisterRequest;
import exception.DuplicateEmailException;
import exception.DuplicateNicknameException;
import exception.UserNotFoundException;
import service.UserRegisterService;

/**
 * 회원가입, 탈퇴 요청을 처리하기 위한 컨트롤러
 * 
 * @author a
 *
 */

@Controller
@RequestMapping("/user")
public class UserRegisterController {

	@Autowired
	private UserRegisterService userRegisterService;
	
	public void setRegisterService(
			UserRegisterService userRegisterService) {
		this.userRegisterService = userRegisterService;
	}
	
	@RequestMapping("/register")
	public String registerForm() {
		return "user/registerForm";
	}
	
	@PostMapping("/registerDo")
	public String registerDo(@Valid UserRegisterRequest userRegisterRequest, Errors errors) {
		if(errors.hasErrors()) return "user/registerForm";
		
		try {
			userRegisterService.regist(userRegisterRequest);
			return "redirect:/main";
		} catch (DuplicateEmailException e) {
			errors.rejectValue("email", "duplicate");
			return "user/registerForm";
		} catch (DuplicateNicknameException e) {
			errors.rejectValue("nickname", "duplicate");
			return "user/registerForm";
		}
	}
	
	@RequestMapping("/withdraw")
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