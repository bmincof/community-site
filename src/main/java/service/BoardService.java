package service;

import org.springframework.stereotype.Service;

import dao.BoardDao;
import dto.BoardListResponse;
import vo.PageVo;
import vo.SearchVo;

@Service
public class BoardService {

	private final BoardDao boardDao;
	
	public BoardService(BoardDao boardDao) {
		super();
		this.boardDao = boardDao;
	}

	public int count(SearchVo searchVo) {
		return boardDao.count(searchVo.getField(), searchVo.getKeyword());
	}
	
	public int countWithType(Integer boardType, SearchVo searchVo) {
		return boardDao.countWithType(boardType, searchVo.getField(), searchVo.getKeyword());
	}
	
	public int countHot(SearchVo searchVo) {
		return boardDao.countHot(searchVo.getField(), searchVo.getKeyword());
	}
	
	public BoardListResponse showBoardList(Integer boardType, PageVo pageVo, SearchVo searchVo) {
		BoardListResponse response = new BoardListResponse(boardType);
		
		response.setNotices(boardDao.selectNotices(boardType));
		response.setHotPosts(boardDao.selectHotPosts(boardType));
		response.setPosts(boardDao.selectPosts(boardType,
				searchVo.getField(),
				searchVo.getKeyword(), 
				pageVo.getPostPerPage(), 
				pageVo.getPostPerPage() * (pageVo.getCurPage()-1)));
		
		return response;
	}
	
	public BoardListResponse showBoardList(PageVo pageVo, SearchVo searchVo) {
		BoardListResponse response = new BoardListResponse();
		
		response.setHotPosts(boardDao.selectHotPosts());
		response.setPosts(boardDao.selectPosts(searchVo.getField(),
				searchVo.getKeyword(), 
				pageVo.getPostPerPage(), 
				pageVo.getPostPerPage() * (pageVo.getCurPage()-1)));
		
		return response;
	}
	
	public BoardListResponse showHotPosts(PageVo pageVo, SearchVo searchVo) {
		BoardListResponse response = new BoardListResponse(-1);
		
		response.setPosts(boardDao.selectAllHotPosts(searchVo.getField(),
				searchVo.getKeyword(), 
				pageVo.getPostPerPage(), 
				pageVo.getPostPerPage() * (pageVo.getCurPage()-1)));
		
		return response;
	}
	
}