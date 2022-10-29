package service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import dao.BoardDao;
import dao.BoardVotesDao;
import dao.ReplyDao;
import dto.BoardDto;
import dto.BoardModifyRequest;
import dto.BoardPostRequest;
import dto.ReplyDto;
import entity.Board;
import exception.BoardNotFoundException;

/**
 * 게시글 상세보기, 작성, 수정, 삭제 기능의 로직을 수행하는 클래스 
 * 
 * @author a
 *
 */

@Service
public class BoardDetailService {

	private final BoardDao boardDao;
	private final BoardVotesDao boardVotesDao;
	private final ReplyDao replyDao;
	
	public BoardDetailService(BoardDao boardDao, BoardVotesDao boardVotesDao, ReplyDao replyDao) {
		super();
		this.boardDao = boardDao;
		this.boardVotesDao = boardVotesDao;
		this.replyDao = replyDao;
	}

	/**
	 * 입력받은 정보와 DB에 필요한 정보를 추가하여 게시글 테이블에 저장을 시도하는 메서드
	 * 
	 * @param req
	 * @param userId
	 */
	
	public void post(BoardPostRequest req, Long userId) {
		Board newPost = new Board(req.getTitle(), req.getContent(),
				userId, LocalDateTime.now(), req.getType(), req.getIsNotice());
		
		boardDao.insert(newPost);
	}
	
	/**
	 * boardId가 일치하는 게시글의 상세 정보를 반환하는 메서드
	 * 게시글의 상세정보 및 추천, 비추천 정보, 댓글 정보를 포함한다.
	 * 
	 * @param boardId
	 * @return
	 */
	
	public BoardDto showDetail(Long boardId) {		
		BoardDto detail = boardDao.showDetail(boardId);
		
		if(detail == null) {
			throw new BoardNotFoundException();
		}
		detail.setVotes(boardVotesDao.selectByBoardId(boardId));
		detail.setReplies(showReplies(boardId));
		
		return detail;
	}
	
	/**
	 * boardId가 일치하는 게시글의 조회수를 1 증가시킨다.
	 * 
	 * @param boardId
	 */
	
	public void updateViews(Long boardId) {
		boardDao.updateViews(boardId);
	}
	
	/**
	 * boardId가 일치하는 게시글에 추가된 댓글, 추천, 비추천 정보를 삭제하고 해당 게시글을 삭제하는 메서드
	 * 
	 * @param boardId
	 */
	
	public void deletePost(Long boardId) {
		boardVotesDao.deleteByBoardId(boardId);
		replyDao.deleteByBoardId(boardId);
		
		boardDao.delete(boardId);
	}
	
	/**
	 * 게시글의 제목, 내용 및 카테고리를 수정하는 메서드
	 * 
	 * @param req
	 */
	
	public void updatePost(BoardModifyRequest req) {
		boardDao.updatePost(req);
	}
	
	/**
	 * boardId가 일치하는 게시글에 추가된 댓글 및 댓글에 추가된 답글을 반환하는 메서드
	 * 
	 * @param boardId
	 * @return
	 */
	
	public List<ReplyDto> showReplies(Long boardId) {
		List<ReplyDto> parentReplies = replyDao.selectListByBoardId(boardId);
		for(ReplyDto reply : parentReplies) {
			reply.setChildReplies(replyDao.selectListByRef(reply.getReplyId()));
		}
		
		return parentReplies;
	}
	
}