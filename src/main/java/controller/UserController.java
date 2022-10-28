package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import entity.User;
import service.UserInfoChangeService;
import service.UserRegisterService;

/**
 * 회원 정보 관리를 위한 마이페이지 및 관리자 기능 요청을 처리하기 위한 컨트롤러
 * 
 * @author a
 *
 */

@Controller
public class UserController {

	@Autowired
	private UserInfoChangeService userInfoChangeService;
	@Autowired
	private UserRegisterService userRegisterService;
	
	/**
	 * 비밀번호 변경 및 탈퇴 처리를 진행할 수 있는 회원 목록을 보여주는 메서드
	 * 
	 * @param model
	 * @return
	 */
	
	@GetMapping("/admin/list")
	public String showUserLIst(Model model) {
		List<User> users = userInfoChangeService.showUserList();
		model.addAttribute("users",users);
		
		return "user/list";
	}
	
	/**
	 * 관리자가 회원의 비밀번호를 변경할 때 사용되는 메서드
	 * 
	 * @param userId
	 * @param model
	 * @return
	 */
	
	@GetMapping("/admin/changeRequest")
	public String changeRequest(Long userId, Model model) {
		model.addAttribute("userId", userId);
		
		return "user/changePasswordForm";
	}
	
	/**
	 * 관리자가 회원을 탈퇴 처리할 때 사용되는 메서드
	 * 
	 * @param userId
	 * @return
	 */
	
	@GetMapping("/admin/delete")
	public String deleteUser(Long userId) {
		userRegisterService.delete(userId);
		
		return "redirect:/admin/list";
	}
	
	/**
	 * 마이페이지를 보여주는 메서드
	 * 
	 * @return
	 */
	
	@GetMapping("/user/myPage")
	public String myPage() {
		return "user/myPage";
	}
	
}