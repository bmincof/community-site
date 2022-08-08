package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import service.UserFindInfoService;

@Controller
public class UserFindInfoController {

	private UserFindInfoService userFindInfoService;
	
	public void setUserFindInfoService(
			UserFindInfoService userFindInfoService) {
		this.userFindInfoService = userFindInfoService;
	}
	
	/* findInfoForm 에서 이메일 찾기 또는 비밀번호 찾기 선택 */
	
	@RequestMapping("/user/findInfo")
	public String findUserInfo() {
		return "user/findInfoForm";
	}
	
	@RequestMapping("/user/findEmail")
	public String findUserEmail() {
		return "user/findEmailForm";
	}
	
	@PostMapping("/user/findEmailPro")
	public String findEmailPro() {
		return "";
	}
	
	@RequestMapping("/user/findPwd")
	public String findUserPwd() {
		return "user/findPwdForm";
	}
	
	@PostMapping("/user/findPwdPro")
	public String findPwdPro() {
		return "";
	}
}
