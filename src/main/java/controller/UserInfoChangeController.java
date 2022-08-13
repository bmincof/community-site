package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dto.LoginUserDto;
import exception.UserNotFoundException;
import service.UserInfoChangeService;

@Controller
public class UserInfoChangeController {

	private UserInfoChangeService userInfoChangeService;
	
	public void setInfoChangeService(
			UserInfoChangeService userInfoChangeService) {
		this.userInfoChangeService = userInfoChangeService;
	}
	
	//session get하고 user select하는 과정 중복
	
	//changePassword
	@GetMapping("/user/changePassword")
	public String changePasswordForm() {
		return "user/changePasswordForm";
	}
		
	@PostMapping("/user/changePassword")
	public String changePassword(HttpServletRequest req,
							@RequestParam(value="newPassword") String newPassword,
							@RequestParam(value="confirmNewPassword") String confirmNewPassword) {
		if (newPassword.equals(confirmNewPassword)) {
			try {
				LoginUserDto userInfo = (LoginUserDto) req.getSession().getAttribute("loginUserInfo");
				long userId = userInfo.getUserId();
				userInfoChangeService.changePassword(userId, newPassword);
				return "redirect:/user/myPage";
			} catch(UserNotFoundException e) {
				return "user/changePasswordForm";
			}
			
		}
		return "redirect:/user/changePassword";
	}
	
	//changeNickname
	
	@GetMapping("/user/changeNickname")
	public String changeNicknameForm() {
		return "user/changeNicknameForm";
	}
	
	@PostMapping("/user/changeNickname")
	public String changeNickname(HttpServletRequest req,
						@RequestParam(value="newNickname") String newNickname) {
		
		// DB에 새로운 닉네임 업데이트
		try {
			LoginUserDto befInfo = (LoginUserDto) req.getSession().getAttribute("loginUserInfo");
			long userId = befInfo.getUserId();
			userInfoChangeService.changeNickname(userId, newNickname);
			
			// 세션 닉네임 정보 업데이트 -> 추후 수정 필요할 수도
			LoginUserDto aftInfo = new LoginUserDto(userId, befInfo.getEmail(), newNickname);
			req.getSession().setAttribute("loginUserInfo", aftInfo);
			return "redirect:/user/myPage";
		} catch(UserNotFoundException e) {
			return "user/changeNicknameForm";
		}
	}
	
	@GetMapping("/user/changePhoneNumber")
	public String changePhoneNumberForm() {
		return "user/changePhoneNumberForm";
	}
	
	@PostMapping("/user/changePhoneNumber")
	public String changePhoneNumber(HttpServletRequest req,
						@RequestParam(value="newPhoneNumber") String newPhoneNumber) {
		try {
			long userId = ((LoginUserDto) req.getSession().getAttribute("loginUserInfo")).getUserId();
			userInfoChangeService.changePhoneNumber(userId, newPhoneNumber);
			return "redirect:/user/myPage";
		} catch(UserNotFoundException e) {
			return "user/changePhoneNumberForm";
		}
	}
	
	
}
