package controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dto.ReplyAddRequest;
import dto.ReplyModifyRequest;
import exception.BoardNotFoundException;
import exception.ReplyNotFoundException;
import exception.WrongBoardReplyMatchException;
import service.BoardResponseService;
import vo.LoginUserVo;

/**
 * 특정 게시글의 반응(추천, 비추천, 댓글) 요청을 처리하기 위한 컨트롤러
 * 
 * @author a
 *
 */

@Controller
@RequestMapping("/board")
public class BoardResponseController {
	
	@Autowired
	private BoardResponseService boardResponseService;

	/**
	 * 추천 버튼을 눌렀을 때 수행되는 메서드
	 * 해당 유저가 
	 * 1. 게시글에 투표한 적 없을 경우 : 추천 + 1,
	 * 2. 게시글에 투표를 이미 한 경우 :
	 * 2-1. 추천 한 경우 -> 취소
	 * 2-2. 비추천 한 경우 -> 유지
	 * @param boardId
	 * @param req
	 * @return
	 */
	
	@GetMapping("/like/{boardId}")
	public String like(@PathVariable Long boardId, HttpServletRequest req, Model model) {
		try {
			LoginUserVo userInfo = (LoginUserVo) req.getSession().getAttribute("loginUserInfo");
			long userId = userInfo.getUserId();
			
			boardResponseService.like(boardId, userId);
			
			return "redirect:/board/detail/"+boardId;
		} catch (BoardNotFoundException e) {
			model.addAttribute("NotFound","존재하지 않는 게시글입니다.");
			return "notFound";
		}
	}
	
	/**
	 * 비추천 버튼을 눌렀을 때 수행되는 메서드
	 * 해당 유저가 
	 * 1. 게시글에 투표한 적 없을 경우 : 비추천 + 1,
	 * 2. 게시글에 투표를 이미 한 경우 :
	 * 2-1. 추천 한 경우 -> 유지
	 * 2-2. 비추천 한 경우 -> 취소
	 * @param boardId
	 * @param req
	 * @return
	 */
	
	@GetMapping("/hate/{boardId}")
	public String hate(@PathVariable Long boardId, HttpServletRequest req, Model model) {
		try {
			LoginUserVo userInfo = (LoginUserVo) req.getSession().getAttribute("loginUserInfo");
			long userId = userInfo.getUserId();
			
			boardResponseService.hate(boardId, userId);
			
			return "redirect:/board/detail/"+boardId;
		} catch (BoardNotFoundException e) {
			model.addAttribute("NotFound","존재하지 않는 게시글입니다.");
			return "notFound";
		}
	}
	
	/**
	 * 입력받은 정보로 댓글을 추가하는 메서드
	 * 
	 * @param addReq
	 * @param errors
	 * @param req
	 * @param rttr
	 * @return
	 */
	
	@PostMapping("/reply/add")
	public String reply(@Valid ReplyAddRequest addReq, Errors errors, 
			HttpServletRequest req, RedirectAttributes rttr) {
		if(errors.hasErrors()) {
			rttr.addFlashAttribute("msg", "댓글에 공백은 허용되지 않습니다.");
			
			return "redirect:/board/detail/"+addReq.getBoardId();
		}
		
		LoginUserVo userInfo = (LoginUserVo) req.getSession().getAttribute("loginUserInfo");
		addReq.setWriter(userInfo.getUserId());
		
		boardResponseService.addReply(addReq);
		
		return "redirect:/board/detail/"+addReq.getBoardId();
	}
	
	/**
	 * replyId가 일치하는 댓글을 삭제하는 메서드
	 * 
	 * @param replyId
	 * @param boardId
	 * @param model
	 * @return
	 */
	
	@GetMapping("/reply/delete")
	public String deleteReply(Long replyId, Long boardId, Model model) {
		try {
			boardResponseService.deleteReplyContent(boardId, replyId);
			return "redirect:/board/detail/"+boardId;
		} catch (ReplyNotFoundException e) {
			model.addAttribute("NotFound", "삭제되었거나 존재하지 않는 댓글입니다.");
			return "notFound";
		} catch (BoardNotFoundException e) {
			model.addAttribute("NotFound","존재하지 않는 게시글입니다.");
			return "notFound";
		} catch (WrongBoardReplyMatchException e) {
			model.addAttribute("NotFound", "잘못된 요청입니다.");
			return "notFound";
		}
	}
	
	/**
	 * 댓글 수정 폼을 출력하는 메서드
	 * 
	 * @param replyId
	 * @param model
	 * @return
	 */
	
	@GetMapping("/reply/modify")
	public String modifyReplyForm(Long replyId, Model model) {
		try {
			model.addAttribute("reply", boardResponseService.selectReply(replyId));
			return "reply/modifyForm";
		} catch (ReplyNotFoundException e) {
			model.addAttribute("NotFound", "삭제되었거나 존재하지 않는 댓글입니다.");
			return "notFound";
		}
	}
	
	/**
	 * 폼에 입력받은 정보로 댓글을 수정하는 메서드
	 * 
	 * @param req
	 * @param errors
	 * @param model
	 * @return
	 */
	
	@PostMapping("/reply/modifyDo")
	public String modifyReply(@Valid @ModelAttribute("reply") ReplyModifyRequest req,
			Errors errors, Model model) {
		if(errors.hasErrors()) {
			model.addAttribute("msg", "댓글에 공백은 허용되지 않습니다.");
			
			return "reply/modifyForm";
		} 
		try {
			boardResponseService.changeReplyContent(req.getBoardId(), req.getReplyId(), req.getContent());
			return "redirect:/board/detail/"+req.getBoardId();
		} catch (ReplyNotFoundException e) {
			model.addAttribute("NotFound", "삭제되었거나 존재하지 않는 댓글입니다.");
			return "notFound";
		} catch (BoardNotFoundException e) {
			model.addAttribute("NotFound","존재하지 않는 게시글입니다.");
			return "notFound";
		} catch (WrongBoardReplyMatchException e) {
			model.addAttribute("NotFound", "잘못된 요청입니다.");
			return "notFound";
		}
	}
	
}