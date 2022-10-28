package service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import dao.BoardDao;
import dao.BoardVotesDao;
import dao.ReplyDao;
import dto.BoardVotesDto;
import dto.ReplyAddRequest;
import dto.ReplyDto;
import entity.Reply;
import exception.BoardNotFoundException;
import exception.ReplyNotFoundException;
import exception.WrongBoardReplyMatchException;

/**
 * 특정 게시글의 반응(추천, 비추천, 댓글) 로직을 처리하는 메서드
 * 
 * @author a
 *
 */

public class BoardResponseService {

	@Autowired
	private BoardDao boardDao;
	
	@Autowired
	private BoardVotesDao boardVotesDao;
	
	@Autowired
	private ReplyDao replyDao;

	/**
	 * 1. userId로 해당 게시글에 투표한 내역이 있을 경우 :
	 * 투표가 추천이라면 추천 내역을 삭제한다.
	 * 투표가 비추천이라면 아무 행동도 하지 않는다.
	 * 
	 * 2. userId로 해당 게시글에 투표한 내역이 없을 경우 :
	 * userId로 해당 게시글에 추천 내역을 추가한다.
	 * 
	 * @param boardId
	 * @param userId
	 */
	
	public void like(Long boardId, Long userId) {
		if(!boardDao.isExist(boardId)) {
			throw new BoardNotFoundException();
		}
		
		BoardVotesDto vote = boardVotesDao.selectByIds(boardId, userId);
	
		if(vote == null) {
			boardVotesDao.insertUp(boardId, userId);
		}
		else if(!vote.isAbleToVote()) {
			if (vote.getUp() != 0) boardVotesDao.delete(boardId, userId);
		}
	}
	
	/**
	 * 1. userId로 해당 게시글에 투표한 내역이 있을 경우 :
	 * 투표가 비추천이라면 비추천 내역을 삭제한다.
	 * 투표가 추천이라면 아무 행동도 하지 않는다.
	 * 
	 * 2. userId로 해당 게시글에 투표한 내역이 없을 경우 :
	 * userId로 해당 게시글에 비추천 내역을 추가한다.
	 * 
	 * @param boardId
	 * @param userId
	 */
	
	public void hate(Long boardId, Long userId) {
		if(!boardDao.isExist(boardId)) {
			throw new BoardNotFoundException();
		}
		
		BoardVotesDto vote = boardVotesDao.selectByIds(boardId, userId);
		
		if(vote == null ) {
			boardVotesDao.insertDown(boardId, userId);
		}
		else if(!vote.isAbleToVote()) {
			if (vote.getDown()!=0) boardVotesDao.delete(boardId, userId);
		}
	}
	
	/**
	 * 입력받은 정보로 댓글을 추가한다.
	 * 
	 * @param req
	 */
	
	public void addReply(ReplyAddRequest req) {
		Reply newReply = new Reply(req.getBoardId(),
				req.getWriter(),
				req.getRef(),
				req.getContent(),
				LocalDateTime.now());
		replyDao.insert(newReply);
	}
	
	/**
	 * 이미 삭제된 댓글이 아닐 경우, 댓글의 내용 정보를 삭제한다.
	 * 
	 * @param boardId
	 * @param replyId
	 */
	
	public void deleteReplyContent(Long boardId, Long replyId) {
		if(!boardDao.isExist(boardId)) {
			throw new BoardNotFoundException();
		}
		
		ReplyDto reply = selectReply(replyId);
		if(reply.getBoardId() != boardId) {
			throw new WrongBoardReplyMatchException();
		}
			
		replyDao.deleteContent(replyId);
	}
	
	/**
	 * replyId와 일치하는 댓글을 불러온다.
	 * 없을 경우 null을 반환한다.
	 * 
	 * @param replyId
	 * @return
	 */
	
	public ReplyDto selectReply(Long replyId) {
		ReplyDto reply= replyDao.selectByReplyId(replyId);
		
		if(reply == null || reply.getIsDeleted()) {
				throw new ReplyNotFoundException();
		}
		
		return reply;
	}
	
	/**
	 * 입력받은 정보와 일치하는 댓글의 내용을 newContent로 수정한다.
	 * 
	 * @param boardId
	 * @param replyId
	 * @param newContent
	 */
	
	public void changeReplyContent(Long boardId ,Long replyId, String newContent) {
		if(!boardDao.isExist(boardId)) {
			throw new BoardNotFoundException();
		}
		
		ReplyDto reply = selectReply(replyId);
		if(reply.getBoardId() != boardId) {
			throw new WrongBoardReplyMatchException();
		}
		
		replyDao.changeContent(replyId, newContent);
	}
	
}