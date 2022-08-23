package controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.BoardDao;
import dao.UserDao;
import entity.Board;
import entity.User;

@Controller
public class UserController {

	// Have to Change after impl. of Service : Dao -> Service
	UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
//	
//	@GetMapping("/user/test/id/{id}")
//	public String selectionTest(@PathVariable("id") long userId,Model model) {
//		User user = userDao.selectById(userId);
//		model.addAttribute("user",user);
//		
//		return "test/select";
//	}
//	
//	@RequestMapping("/user/test/insert")
//	public String insertTestStep1() {
//		
//		return "test/insert";
//	}
//	
//	@PostMapping("/user/test/insertProcess")
//	public String insertTestStep2() {
//		return "redirect:/user/test/list";
//	}
//	
//	@RequestMapping("/user/test/update")
//	public String updateTestStep1() {
//		return "test/updateProcess";
//	}
//	
//	@PostMapping("/user/test/updateProcess")
//	public String updateTestStep2(@RequestParam(value="userId") long userId, 
//									@RequestParam(value="newName") String newName) {
//		User user = userDao.selectById(userId);
//		user.setNewName(newName);
//		userDao.update(user);
//		return "redirect:/user/test/list";
//	}
	
	@GetMapping("/user/list")
	public String updateTest(Model model) {
		List<User> users = userDao.selectAll();
		model.addAttribute("users",users);
		return "user/list";
	}
//
//	@GetMapping("/user/test/delete/{id}")
//	public String deleteTest(@PathVariable("id") long userId) {
//		userDao.deleteById(userId);
//		return "test/list";
//	}
//	
	
	@RequestMapping("/user/myPage")
	public String userPage() {
		return "user/myPage";
	}
	
}
