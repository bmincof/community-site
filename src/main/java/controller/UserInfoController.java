package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dto.LoginUserDto;
import dto.UserInfoChangeRequest;
import dto.UserInfoFindRequest;
import exception.DuplicateNicknameException;
import exception.UserNotFoundException;
import exception.WrongUserInfoException;
import service.UserInfoFindService;
import service.UserInfoChangeService;

/**
 * 회원정보 변경, 찾기 요청을 처리하기 위한 컨트롤러
 * 
 * @author a
 *
 */


@Controller
@RequestMapping("/user")
public class UserInfoController {

	@Autowired
	private UserInfoChangeService userInfoChangeService;
	
	@Autowired
	private UserInfoFindService userInfoFindService;
	
	public void setInfoChangeService(
			UserInfoChangeService userInfoChangeService) {
		this.userInfoChangeService = userInfoChangeService;
	}
	
	
	public void setUserFindInfoService(
		UserInfoFindService userInfoFindService) {
		this.userInfoFindService = userInfoFindService;
	}
	
	// 회원정보 변경 요청 처리
	
	@GetMapping("/changePassword")
	public String changePasswordForm() {
		return "user/changePasswordForm";
	}
	
	/**
	 * 회원의 닉네임 정보를 입력받은 새로운 닉네임으로 변경한다.
	 * 
	 * @param req
	 * @param errors
	 * @param hreq
	 * @param model
	 * @return
	 */
		
	@PostMapping("/changePassword")
	public String changePassword(@Valid UserInfoChangeRequest req, Errors errors, HttpServletRequest hreq, Model model) {
		if(errors.hasErrors()) return "user/changePasswordForm";
		
		try {
			userInfoChangeService.changePassword(req);
			Object userInfo = hreq.getSession().getAttribute("loginUserInfo");
			if(userInfo == null) {
				return "main";
			}
			else {
				return "user/myPage";
			}
		} catch(UserNotFoundException e) {
			model.addAttribute("msg", "회원정보를 찾는 중 오류가 발생했습니다. 종료 후 다시 시도해주십시오.");
			return "user/changePasswordForm";
		}	
	}
	
	
	@GetMapping("/changeNickname")
	public String changeNicknameForm() {
		return "user/changeNicknameForm";
	}
	
	/**
	 * 회원의 닉네임 정보를 입력받은 새로운 닉네임으로 변경한다.
	 * 변경하려는 닉네임을 사용하는 회원이 이미 있을 경우 알림과 함께 회원가입 폼을 다시 출력한다.
	 * 
	 * @param req
	 * @param errors
	 * @param hreq
	 * @param model
	 * @return
	 */
	
	@PostMapping("/changeNickname")
	public String changeNickname(@Valid UserInfoChangeRequest req, Errors errors,HttpServletRequest hreq, Model model) {
		if(errors.hasErrors()) return "user/changeNicknameForm";
		
		try {
			userInfoChangeService.changeNickname(req);
			
			// 세션 닉네임 정보 업데이트
			LoginUserDto befInfo = (LoginUserDto) hreq.getSession().getAttribute("loginUserInfo");
			LoginUserDto aftInfo = new LoginUserDto(req.getUserId(), befInfo.getEmail(), req.getNewNickname());
			hreq.getSession().setAttribute("loginUserInfo", aftInfo);
			
			return "user/myPage";
		}catch (DuplicateNicknameException e) {
			model.addAttribute("msg", "중복된 닉네임입니다.");
			return "user/changeNicknameForm";
		}catch(UserNotFoundException e) {
			model.addAttribute("msg", "회원정보를 찾는 중 오류가 발생했습니다. 종료 후 다시 시도해주십시오.");
			return "user/changeNicknameForm";
		}
	}
	
	@GetMapping("/changePhoneNumber")
	public String changePhoneNumberForm() {
		return "user/changePhoneNumberForm";
	}
	
	/**
	 * 회원의 휴대전화번호 정보를 입력받은 새로운 휴대전화번호로 변경한다.
	 * 
	 * @param req
	 * @param errors
	 * @param model
	 * @return
	 */
	
	@PostMapping("/changePhoneNumber")
	public String changePhoneNumber(@Valid UserInfoChangeRequest req, Errors errors, Model model) {
		if(errors.hasErrors()) return "user/changePhoneNumberForm";
		
		try {
			userInfoChangeService.changePhoneNumber(req);
			
			return "user/myPage";
		} catch(UserNotFoundException e) {
			model.addAttribute("msg", "회원정보를 찾는 중 오류가 발생했습니다. 종료 후 다시 시도해주십시오.");
			return "user/changePhoneNumberForm";
		}
	}
	
	// 회원정보 찾기 요청 처리
	
	@GetMapping("/findEmail")
	public String findUserEmailForm() {
		return "user/findEmailForm";
	}
	
	/**
	 * 입력한 회원 정보가 일치하면 회원 정보로 가입된 이메일을 출력한다.
	 * 
	 * @param req
	 * @param errors
	 * @param model
	 * @return
	 */
	
	@PostMapping("/findEmail")
	public String findUserEmail(@Valid UserInfoFindRequest req, Errors errors, Model model) {
		if(errors.hasErrors()) return "user/findEmailForm";
		
		try {
			List<String> foundEmails = userInfoFindService.findEmail(req);
			model.addAttribute("foundEmails", foundEmails);
			
			return "user/foundEmails";
		} catch(UserNotFoundException e) {
			model.addAttribute("msg","일치하는 회원정보가 없습니다.");
			
			return "user/findEmailForm"; 
		}
	}
	
	@GetMapping("/findPwd")
	public String findUserPwdForm() {
		return "user/findPwdForm";
	}
	
	/**
	 * 입력한 회원 정보가 일치하면 비밀번호 변경 폼을 출력한다.
	 * 
	 * @param req
	 * @param errors
	 * @param model
	 * @return
	 */
	
	@PostMapping("/findPwd")
	public String findUserPwd(@Valid UserInfoFindRequest req, Errors errors, Model model) {
		if(errors.hasErrors()) return "user/findPwdForm"; 
		
		try {
			Long userId = userInfoFindService.findPassword(req);
			model.addAttribute("userId", userId);
			
			return "user/changePasswordForm";
		} catch(UserNotFoundException | WrongUserInfoException e) {
			model.addAttribute("msg","일치하는 회원정보가 없습니다.");
			
			return "user/findPwdForm";
		}
	}
	
}