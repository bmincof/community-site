package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dao.ReplyDao;
import vo.ReplyVo;

public class ReplyService {

	@Autowired
	private ReplyDao replyDao;
	
	public void setReplyDao(ReplyDao replyDao) {
		this.replyDao = replyDao;
	}
	
	public List<ReplyVo> showReplies(long boardId) {
		List<ReplyVo> parentReplies = replyDao.selectListByBoardId(boardId);
		for(ReplyVo reply : parentReplies) {
			reply.setChildReplies(replyDao.selectListByRef(reply.getReplyId()));
		}
		//for each parentReply, setReplies(replyDao.selectListByRef(parentReply.getReplyId());
		
		return parentReplies;
	}
	
}
