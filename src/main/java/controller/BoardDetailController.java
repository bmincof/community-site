package controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dto.BoardDto;
import dto.BoardModifyRequest;
import dto.BoardPostRequest;
import exception.BoardNotFoundException;
import service.BoardDetailService;
import vo.LoginUserVo;

/**
 * 게시글 상세보기, 작성, 수정, 삭제 요청을 처리하기 위한 컨트롤러
 * 
 * @author a
 *
 */

@Controller
@RequestMapping("/board")
public class BoardDetailController {
	
	@Autowired
	private BoardDetailService boardDetailService;

	/**
	 * 게시글 작성 화면을 출력하는 메서드
	 * 
	 * @return
	 */
	
	@RequestMapping("/write")
	public String boardWriteForm() {
		return "board/writeForm";
	}
	
	/**
	 * 게시글 작성 폼에 입력받은 정보로 게시글을 작성하는 메서드
	 * 
	 * @param postReq
	 * @param errors
	 * @param req
	 * @param model
	 * @return
	 */
	
	@PostMapping("/writeDo")
	public String boardWritePro(@Valid BoardPostRequest postReq, Errors errors, HttpServletRequest req, Model model) {
		if(errors.hasErrors()) {
			model.addAttribute("msg", "제목과 내용은 공백없이 각각 2글자, 1글자 이상이어야 합니다.");
			
			return "board/writeForm";
		}
		
		LoginUserVo userInfo = (LoginUserVo) req.getSession().getAttribute("loginUserInfo");
		boardDetailService.post(postReq, userInfo.getUserId());
		
		return "redirect:/board/list";
	}
	
	/**
	 * boardId가 일치하는 게시글의 상세내용을 보여주고 조회수를 1 증가시키는 메서드
	 * 글 번호, 작성자, 작성일, 제목, 내용, 추천, 비추천, 댓글을 출력한다.
	 * 
	 * @param boardId
	 * @param model
	 * @return
	 */
	
	@RequestMapping("/detail/{boardId}")
	public String showDetail(@PathVariable Long boardId, Model model) {
		try {
			boardDetailService.updateViews(boardId);
			BoardDto detail = boardDetailService.showDetail(boardId);
			
			model.addAttribute("detail", detail);
			return "board/detail";
		} catch (BoardNotFoundException e) {
			model.addAttribute("NotFound", "존재하지 않는 게시글입니다.");
			return "notFound";
		}
	}
	
	/**
	 * 게시글 수정 화면을 출력하는 메서드
	 * 
	 * @param req
	 * @param model
	 * @return
	 */
	
	@PostMapping("/modify")
	public String boardModifyForm(BoardModifyRequest req, Model model) {
		
		model.addAttribute("modifyRequest", req);
		return "board/modifyForm";
	}
	
	/**
	 * 입력받은 정보로 게시글의 제목과 내용을 수정하는 메서드
	 * 
	 * @param req
	 * @param errors
	 * @param model
	 * @return
	 */
	
	@PostMapping("/modifyDo")
	public String boardModify(@Valid @ModelAttribute("modifyRequest") BoardModifyRequest req, Errors errors, Model model) {
		if(errors.hasErrors()) {
			model.addAttribute("msg", "제목과 내용은 공백없이 각각 2글자, 1글자 이상이어야 합니다.");
			
			return "board/modifyForm";
		}
		
		boardDetailService.updatePost(req);
		return "redirect:/board/detail/"+req.getBoardId();
	}
	
	/**
	 * boardId가 일치하는 게시글의 댓글들과 추천, 비추천, 게시글을 삭제하는 메서드
	 * 
	 * @param boardId
	 * @param boardType
	 * @return
	 */
	
	@PostMapping("/delete")
	public String boardDelete(Long boardId, Integer boardType) {
		boardDetailService.deletePost(boardId);
		
		return "redirect:/board/list/"+boardType;
	}
		
}