package service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dao.BoardDao;
import dao.BoardVotesDao;
import dto.BoardDto;
import dto.BoardPostRequest;
import dto.BoardVotesDto;
import entity.Board;
import vo.PageVo;
import vo.SearchVo;

public class BoardService {

	@Autowired
	private BoardDao boardDao;
	@Autowired
	private BoardVotesDao boardVotesDao;
	
	public void setBoardDao(BoardDao boardDao) {
		this.boardDao = boardDao;
	}
	
	public void setBoardVotesDao(BoardVotesDao boardVotesDao) {
		this.boardVotesDao = boardVotesDao;
	}
	
	public int count(int type, SearchVo searchVo) {
		return boardDao.count(type, searchVo.getField(), searchVo.getKeyword());
	}
	
	public void post(BoardPostRequest req, long userId) {
		Board newPost = new Board(req.getTitle(), req.getContent(),
				userId, LocalDateTime.now(), req.getIsNotice());
		
		boardDao.insert(newPost);
	}
	
	public List<Board> boardList() {
		return boardDao.selectAll();
	}
	
	public List<BoardDto> showList(int type ,SearchVo searchVo, PageVo pageVo) {
		return boardDao.selectList(type,
								searchVo.getField(),
								searchVo.getKeyword(), 
								pageVo.getPostPerPage(), 
								pageVo.getPostPerPage() * (pageVo.getCurPage()-1));
	}
	
	public BoardDto showDetail(long boardId) {
		return boardDao.showDetail(boardId);
	}
	
	public void updateViews(long boardId) {
		boardDao.updateViews(boardId);
	}
	
	public void delete(long boardId) {
		boardDao.delete(boardId);
	}
	
	public void updatePost(BoardDto boardDto) {
		boardDao.updatePost(boardDto);
	}
	
	public BoardVotesDto showVotes(long boardId) {
		BoardVotesDto result = new BoardVotesDto();
		result.setUp(boardVotesDao.countUp(boardId));
		result.setDown(boardVotesDao.countDown(boardId));
		return result;
	}
	
	public void like(long boardId, long userId) {
		boardVotesDao.insertUp(boardId, userId);
	}
	
	public void hate(long boardId, long userId) {
		boardVotesDao.insertDown(boardId, userId);
	}
	
	public void cancelVotes(long boardId, long userId) {
		boardVotesDao.delete(boardId, userId);
	}
	
	public List<BoardDto> showHotPost(int type, PageVo pageVo) {
		return boardDao.selectHotPosts(type, pageVo);
	}
	
	public List<BoardDto> showNotice(int type, PageVo pageVo){
		return boardDao.selectNotice(type, pageVo);
	}
	
}
