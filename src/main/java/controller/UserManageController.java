package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import dto.LoginUserDto;

@Controller
public class UserManageController {

//	@PostMapping("/admin/userList")
//	public String showUserList(HttpServletRequest req) {
//		LoginUserDto user = (LoginUserDto) req.getSession().getAttribute("loginUserInfo");
//		if(user.isAdmin()) {
//			return "user/userList";
//		} else {
//			return "redirect:/"; 
//		}
//	}
}
