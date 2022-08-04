package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dto.UserRegisterDto;
import service.UserRegisterService;

@Controller
public class UserRegisterController {

	private UserRegisterService userRegisterService;
	
	public void setRegisterService(
			UserRegisterService userRegisterService) {
		this.userRegisterService = userRegisterService;
	}
	
	@RequestMapping("/user/register")
	public String registerForm() {
		return "user/registerForm";
	}
	
	@PostMapping("/user/registerPro")
	public String registerPro(UserRegisterDto userRegisterDto) {
		
		userRegisterService.regist(userRegisterDto);
		return "redirect:/user/list";
	}
	
	@RequestMapping("/user/withdraw/{id}")
	public String withdrawCheck(@PathVariable("id") long userId) {
		return "user/withdrawCheck";
	}
	
	@PostMapping("/user/withdrawPro/{id}")
	public String withdrawPro(@RequestParam(value="check", defaultValue = "false") boolean check,
								@PathVariable("id") long userId) {
		if(!check) {
			return "redirect:/user/withdraw/"+userId;
		}
		else {
			userRegisterService.delete(userId);
			return "redirect:/user/list";
		}
	}
	
}
