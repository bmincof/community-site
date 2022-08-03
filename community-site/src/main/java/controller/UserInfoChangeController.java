package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import service.UserInfoChangeService;

@Controller
public class UserInfoChangeController {

	private UserInfoChangeService userInfoChangeService;
	
	public void setInfoChangeService(
			UserInfoChangeService userInfoChangeService) {
		this.userInfoChangeService = userInfoChangeService;
	}
	
	//changePassword
	@RequestMapping("/user/changePassword/{id}")
	public String changePasswordForm(@PathVariable("id") long userId) {
		//form to get new password
		return "user/changePasswordForm";
	}
	
	@PostMapping("/user/changePasswordPro/{id}")
	public String changePasswordPro(@PathVariable("id") long userId,
							@RequestParam(value="newPassword") String newPassword,
							@RequestParam(value="confirmNewPassword") String confirmNewPassword) {
		if (newPassword.equals(confirmNewPassword)) {
			userInfoChangeService.changePassword(userId, newPassword);
			return "redirect:/user/list";
		}
		return "redirect:/user/changePasswordPro"+userId;
	}
	//changeInfo
	@RequestMapping("/user/changeInfo/{id}")
	public String changeInfoForm(@PathVariable("id") long userId) {
		//form to get new info
		return "user/changeInfoForm";
	}
	
	@PostMapping("/user/changeInfoPro/{id}")
	public String changeInfoPro(@PathVariable("id") long userId,
						@RequestParam(value="newNickname") String newNickname,
						@RequestParam(value="newPhoneNumber") String newPhoneNumber) {
		
		userInfoChangeService.changeInfo(userId, newNickname, newPhoneNumber);
		return "redirect:/user/list";
	}
	
}
