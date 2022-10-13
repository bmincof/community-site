package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.UserDao;
import entity.User;

@Controller
@RequestMapping("/user")
public class UserController {

	// Have to Change after impl. of Service : Dao -> Service
	@Autowired
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@GetMapping("/list")
	public String updateTest(Model model) {
		List<User> users = userDao.selectAll();
		model.addAttribute("users",users);
		return "user/list";
	}
	
	@GetMapping("/myPage")
	public String myPage() {
		return "user/myPage";
	}
	
}
