package controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import exception.UserNotFoundException;
import exception.WrongUserInfoException;
import service.UserFindInfoService;

@Controller
public class UserFindInfoController {

	private UserFindInfoService userFindInfoService;
	
	public void setUserFindInfoService(
		UserFindInfoService userFindInfoService) {
		this.userFindInfoService = userFindInfoService;
	}
	
	@GetMapping("/user/findEmail")
	public String findUserEmailForm() {
		return "user/findEmailForm";
	}
	
	@PostMapping("/user/findEmail")
	public ModelAndView findUserEmail(@RequestParam(value = "name") String name,
								@RequestParam(value = "phoneNumber") String phoneNumber,
								ModelAndView mav) {
		try {
			List<String> foundEmails = userFindInfoService.findEmail(name, phoneNumber);
			mav.addObject("foundEmails", foundEmails);
			mav.setViewName("user/foundEmails");
			return mav;
		} catch(UserNotFoundException | WrongUserInfoException e) {
			mav.addObject("msg","일치하는 회원정보가 없습니다.");
			mav.addObject("href","/community-site/");
			mav.setViewName("alert");
			return mav; 
		}
	}
	
	@GetMapping("/user/findPwd")
	public String findUserPwdForm() {
		return "user/findPwdForm";
	}
	
	// 이후에 DB 비밀번호 반환 대신, 비밀번호 변경하도록 수정할 것
	
	@PostMapping("/user/findPwd")
	public ModelAndView findUserPwd(@RequestParam(value = "email") String email,
							@RequestParam(value = "name") String name,
							@RequestParam(value = "phoneNumber") String phoneNumber,
							ModelAndView mav) {
		try {
			String foundPwd = userFindInfoService.findPassword(email, name, phoneNumber);
			mav.addObject("foundPwd", foundPwd);
			mav.setViewName("user/foundPwd");
			return mav;
		} catch(UserNotFoundException | WrongUserInfoException e) {
			mav.addObject("msg","일치하는 회원정보가 없습니다.");
			mav.addObject("href", "/community-site/");
			mav.setViewName("alert");
			return mav;
		}
	}
}
