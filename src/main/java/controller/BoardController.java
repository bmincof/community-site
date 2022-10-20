package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dao.BoardVotesDao;
import dto.BoardDto;
import dto.BoardPostRequest;
import dto.BoardVotesDto;
import service.BoardService;
import service.ReplyService;
import vo.LoginUserVo;
import vo.PageVo;
import vo.ReplyVo;
import vo.SearchVo;

@RequestMapping("/board")
@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	@Autowired
	private BoardVotesDao boardVotesDao;
	@Autowired
	private ReplyService replyService;
	
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
	
	public void setBoardVotesDao(BoardVotesDao boardVotesDao) {
		this.boardVotesDao = boardVotesDao;
	}
	
	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}
		
	@RequestMapping("/write")
	public String boardWriteForm() {
		return "board/writeForm";
	}
	
	@PostMapping("/writePro")
	public String boardWritePro(@Valid BoardPostRequest postReq, Errors errors, HttpServletRequest req) {
		LoginUserVo userInfo = (LoginUserVo) req.getSession().getAttribute("loginUserInfo");
		boardService.post(postReq, userInfo.getUserId());
		
		return "redirect:/board/list";
	}
	
	@RequestMapping("/list/{boardType}")
	public String boardList(Model model,
			@PathVariable(value = "boardType") int boardType,
			@RequestParam(value = "field", required = false) String field,
			@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		
		SearchVo searchVo = new SearchVo(field, keyword);
		PageVo pageVo = new PageVo(boardService.count(boardType, searchVo), page);					// testCode
		List<BoardDto> lists = boardService.showList(boardType, searchVo, pageVo);
		List<BoardDto> hotPosts = boardService.showHotPost(boardType, pageVo);
		List<BoardDto> notices = boardService.showNotice(boardType, pageVo);
		
		model.addAttribute("lists",lists);
		model.addAttribute("hotPosts", hotPosts);
		model.addAttribute("notices", notices);
		model.addAttribute("pageVo", pageVo);
		model.addAttribute("searchVo", searchVo);
		return "board/list";
	}
	
	@RequestMapping("detail/{boardId}")
	public String showDetail(@PathVariable long boardId, Model model) {
		boardService.updateViews(boardId);
		BoardDto detail = boardService.showDetail(boardId);
		detail.setVotes(boardVotesDao.selectByBoardId(boardId));
		List<ReplyVo> replies = replyService.showReplies(boardId);
		
		model.addAttribute("detail", detail);
		model.addAttribute("replies", replies);
		return "board/detail";
	}
	
	@GetMapping("/modify/{boardId}")
	public String boardModifyForm(@PathVariable long boardId, Model model) {
		BoardDto detail = boardService.showDetail(boardId);
		model.addAttribute("detail", detail);
		return "board/modifyForm";
	}
	
	@PostMapping("/modify/do")
	public String boardModify(@Valid BoardDto boardDto, Errors errors) {
		boardService.updatePost(boardDto);
		return "redirect:/board/detail/"+boardDto.getBoardId();
	}
	
	@GetMapping("/delete/{boardId}")
	public String boardDelete(@PathVariable long boardId, int boardType) {
		boardService.delete(boardId);
		return "redirect:/board/list/"+boardType;
	}
	
	/**
	 * 해당 유저가 
	 * 1. 게시글에 투표한 적 없을 경우 -> 추천 + 1,
	 * 2. 게시글에 투표를 이미 한 경우
	 * -> 추천 한 경우 -> 취소
	 * -> 비추천 한 경우 -> 유지
	 * @param boardId
	 * @param req
	 * @return
	 */
	
	@GetMapping("/like/{boardId}")
	public String like(@PathVariable long boardId, HttpServletRequest req) {
		LoginUserVo userInfo = (LoginUserVo) req.getSession().getAttribute("loginUserInfo");
		long userId = userInfo.getUserId();
		
		BoardVotesDto vote = boardVotesDao.selectByIds(boardId, userId);
		
		if(vote == null ) {
			boardVotesDao.insertUp(boardId, userId);
		}
		else if(!vote.isAbleToVote()) {
			if (vote.getUp() != 0) boardVotesDao.delete(boardId, userId);
		}
		return "redirect:/board/detail/"+boardId;
	}
	
	/**
	 * 해당 유저가 
	 * 1. 게시글에 투표한 적 없을 경우 -> 비추천 + 1,
	 * 2. 게시글에 투표를 이미 한 경우
	 * -> 추천 한 경우 -> 유지
	 * -> 비추천 한 경우 -> 취소
	 * @param boardId
	 * @param req
	 * @return
	 */
	
	@GetMapping("/hate/{boardId}")
	public String hate(@PathVariable long boardId, HttpServletRequest req) {
		LoginUserVo userInfo = (LoginUserVo) req.getSession().getAttribute("loginUserInfo");
		long userId = userInfo.getUserId();
		
		BoardVotesDto vote = boardVotesDao.selectByIds(boardId, userId);
		
		if(vote == null ) {
			boardVotesDao.insertDown(boardId, userId);
		}
		else if(!vote.isAbleToVote()) {
			if (vote.getDown()!=0) boardVotesDao.delete(boardId, userId);
		}
		return "redirect:/board/detail/"+boardId;
	}
	
}
