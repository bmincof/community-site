package service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dao.BoardDao;
import dto.BoardPostRequest;
import entity.Board;

public class BoardService {

	@Autowired
	private BoardDao boardDao;
	
	public void setBoardDao(BoardDao boardDao) {
		this.boardDao = boardDao;
	}
	
	public void post(BoardPostRequest req, long userId) {
		Board newPost = new Board(req.getTitle(), req.getContent(),
				userId, LocalDateTime.now());
		
		boardDao.insert(newPost);
	}
	
	public List<Board> boardList() {
		return boardDao.selectAll();
	}
	
}
