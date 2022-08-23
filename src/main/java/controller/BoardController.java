package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dto.BoardPostRequest;
import dto.LoginUserDto;
import entity.Board;
import service.BoardService;

@Controller
public class BoardController {

	private BoardService boardService;
	
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@RequestMapping("/board/list")
	public String boardList(Model model) {
		List<Board> boards = boardService.boardList();
		model.addAttribute("boards",boards);
		return "board/list";
	}
	
	@PostMapping("/board/write")
	public String boardWrite() {
		return "board/writeForm";
	}
	
	@PostMapping("/board/writePro")
	public String boardWritePro(BoardPostRequest postReq, HttpServletRequest req) {
		LoginUserDto userInfo = (LoginUserDto) req.getSession().getAttribute("LoginUserInfo");
		boardService.post(postReq, userInfo.getUserId());
		
		return "redirect:/board/list";
	}
	
}
