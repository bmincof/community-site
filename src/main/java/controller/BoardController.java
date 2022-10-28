package controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;

import dao.BoardVotesDao;
import dto.BoardDto;
import dto.BoardListResponse;
import dto.BoardModifyRequest;
import dto.BoardPostRequest;
import dto.BoardVotesDto;
import dto.ReplyDto;
import service.BoardDetailService;
import service.BoardService;
import vo.LoginUserVo;
import vo.PageVo;
import vo.SearchVo;

/**
 * 게시글 목록 불러오기 요청을 처리하기 위한 컨트롤러
 * 
 * @author a
 *
 */

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
		
	/**
	 * 인기글과 검색 조건에 일치하는 공지가 아닌 게시글들의 목록을 카테고리에 관계없이 페이징하여 출력하는 메서드
	 * 
	 * @param page
	 * @param searchVo
	 * @param model
	 * @return
	 */
	
	@RequestMapping("/list")
	public String boardAllList(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			SearchVo searchVo, Model model) {
		PageVo pageVo = new PageVo(boardService.count(searchVo), page);
		
		BoardListResponse response= boardService.showBoardList(pageVo, searchVo);
		
		model.addAttribute("lists", response);
		model.addAttribute("pageVo", pageVo);
		model.addAttribute("searchVo", searchVo);
		
		return "board/list";
	}
	
	/**
	 * 검색 조건에 일치하는 역대 인기 게시글들의 목록을 카테고리에 관계없이 페이징하여 출력하는 메서드
	 * 
	 * @param page
	 * @param searchVo
	 * @param model
	 * @return
	 */
	
	@RequestMapping("/list/hot")
	public String boardHotList(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			SearchVo searchVo, Model model) {
		PageVo pageVo = new PageVo(boardService.countHot(searchVo), page);
		
		BoardListResponse response= boardService.showHotPosts(pageVo, searchVo);
		
		model.addAttribute("lists", response);
		model.addAttribute("pageVo", pageVo);
		model.addAttribute("searchVo", searchVo);
		
		return "board/list";
	}
	
	/**
	 * 게시판의 카테고리에 일치하는 공지, 인기글과
	 * 게시판의 카테고리 및 검색 조건에 일치하는 공지가 아닌 게시글들의 목록을 페이징하여 출력하는 메서드
	 * 
	 * @param boardType
	 * @param page
	 * @param searchVo
	 * @param model
	 * @return
	 */
	
	@RequestMapping("/list/{boardType}")
	public String boardTypeList(@PathVariable(value = "boardType") Integer boardType,
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			SearchVo searchVo, Model model) {
		PageVo pageVo = new PageVo(boardService.countWithType(boardType, searchVo), page);
		
		BoardListResponse response= boardService.showBoardList(boardType, pageVo, searchVo);
		
		model.addAttribute("lists", response);
		model.addAttribute("pageVo", pageVo);
		model.addAttribute("searchVo", searchVo);
		
		return "board/list";
	}
	
}