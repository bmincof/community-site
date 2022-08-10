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
	
	@RequestMapping("/user/changePasswordPro/{id}")
	public String changePassword(@PathVariable("id") long userId,
							@RequestParam(value="newPassword") String newPassword,
							@RequestParam(value="confirmNewPassword") String confirmNewPassword) {
		if (newPassword.equals(confirmNewPassword)) {
			userInfoChangeService.changePassword(userId, newPassword);
			return "redirect:/user/list";
		}
		return "redirect:/user/changePassword/"+userId;
	}
	
	@RequestMapping("/user/changePassword/{id}")
	public String changePasswordForm(@PathVariable("id") long userId) {
		return "user/changePasswordForm";
	}
	
	//changeNickname
	
	@PostMapping("/user/changeNickname/{id}")
	public String changeNickname(@PathVariable("id") long userId,
						@RequestParam(value="newNickname") String newNickname) {
		
		userInfoChangeService.changeNickname(userId, newNickname);
		return "redirect:/user/list";
	}
	
	@PostMapping("/user/changePhoneNumber/{id}")
	public String changePhoneNumber(@PathVariable("id") long userId,
						@RequestParam(value="newPhoneNumber") String newPhoneNumber) {
		userInfoChangeService.changePhoneNumber(userId, newPhoneNumber);
		return "redirect:/user/list";
	}
	
	
}
