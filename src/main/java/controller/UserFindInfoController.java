package controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String findUserEmail(@RequestParam(value = "name") String name,
								@RequestParam(value = "phoneNumber") String phoneNumber,
								Model model) {
		List<String> foundEmails = userFindInfoService.findEmail(name, phoneNumber);
		model.addAttribute("foundEmails", foundEmails);
		return "user/foundEmails";
	}
	
	@GetMapping("/user/findPwd")
	public String findUserPwdForm() {
		return "user/findPwdForm";
	}
	
	// 이후에 DB 비밀번호 반환 대신, 비밀번호 변경하도록 수정할 것
	
	@PostMapping("/user/findPwd")
	public String findUserPwd(@RequestParam(value = "email") String email,
							@RequestParam(value = "name") String name,
							@RequestParam(value = "phoneNumber") String phoneNumber,
							Model model) {
		String foundPwd = userFindInfoService.findPassword(email, name, phoneNumber);
		model.addAttribute("foundPwd", foundPwd);
		return "user/foundPwd";
	}
}
