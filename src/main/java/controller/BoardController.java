package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dto.BoardDto;
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
		
	@RequestMapping("/board/write")
	public String boardWrite() {
		return "board/writeForm";
	}
	
	@PostMapping("/board/writePro")
	public String boardWritePro(BoardPostRequest postReq, HttpServletRequest req) {
		LoginUserDto userInfo = (LoginUserDto) req.getSession().getAttribute("loginUserInfo");
		boardService.post(postReq, userInfo.getUserId());
		
		return "redirect:/board/list";
	}
	
	@RequestMapping("/board/list")
	public String listTest(Model model) {
		List<BoardDto> lists = boardService.showList();
		model.addAttribute("lists",lists);
		return "board/list";
	}
	
	@RequestMapping("/board/{boardId}")
	public String showDetail(@PathVariable long boardId, Model model) {
		boardService.updateViews(boardId);
		BoardDto detail = boardService.showDetail(boardId);
		model.addAttribute("detail", detail);
		return "board/detail";
	}
	
}
