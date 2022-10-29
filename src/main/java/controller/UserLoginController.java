package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dto.UserLoginRequest;
import exception.UserNotFoundException;
import exception.WrongIdPasswordException;
import service.UserLoginService;
import vo.LoginUserVo;

/**
 * 로그인, 로그아웃 요청을 처리하기 위한 컨트롤러
 * 
 * @author a
 *
 */

@Controller
@RequestMapping("/user")
public class UserLoginController {

	private final UserLoginService userLoginService;
	
	public UserLoginController(UserLoginService userLoginService) {
		super();
		this.userLoginService = userLoginService;
	}

	@RequestMapping("/login")
	public String userLogin() {
		
		return "user/loginForm";
	}
	
	/**
	 * 로그인 요청 폼에서 입력받은 값에 이상이 없을 경우 로그인을 시도한다.
	 * Exception이 발생하지 않을 경우 session으로 회원 정보를 전달하고 메인페이지로 이동한다.
	 * Exception이 발생할 경우 에러메시지를 출력하고 로그인 화면으로 다시 돌아간다.
	 * 
	 * @param loginReq
	 * @param errors
	 * @param req
	 * @param model
	 * @return
	 */
	
	@PostMapping("/loginDo")
	public String userLoginPro(@Valid UserLoginRequest loginReq, Errors errors, HttpServletRequest req, Model model) {
		if(errors.hasErrors()) return "user/loginForm";
		
		try {
			HttpSession session = req.getSession();
			LoginUserVo loginUserInfo = userLoginService.userLogin(loginReq.getEmail(), loginReq.getPassword());
			
			session.setAttribute("loginUserInfo", loginUserInfo);
			
			return "redirect:/main";
		} catch (UserNotFoundException | WrongIdPasswordException e) {
			model.addAttribute("msg", "일치하는 회원 정보가 없습니다.");
			
			return "user/loginForm";
		}
		
	}
	
	/**
	 * 로그아웃 요청이 들어올 경우 설정된 모든 세션을 지운다.
	 * 
	 * @param req
	 * @return
	 */
	
	@GetMapping("/logout")
	public String userLogout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.invalidate();
		return "redirect:/main";
	}
	
}