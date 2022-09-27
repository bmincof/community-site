package vo;

import java.time.LocalDateTime;
import java.util.List;

public class ReplyVo {

	private long replyId;
	private long boardId;
	private long writer;
	private String writerName;
	private String content;
	private LocalDateTime writtenDate;
	private List<ReplyVo> childReplies;
	
	//setter
	public void setReplyId(long replyId) {
		this.replyId = replyId;
	}
	
	public void setBoardId(long boardId) {
		this.boardId = boardId;
	}
	
	public void setWriter(long writer) {
		this.writer = writer;
	}
	
	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public void setWrittenDate(LocalDateTime writtenDate) {
		this.writtenDate = writtenDate;
	}
	
	public void setChildReplies(List<ReplyVo> childReplies) {
		this.childReplies = childReplies;
	}
	
	//getter
	public long getReplyId() {
		return replyId;
	}
	
	public long getBoardId() {
		return boardId;
	}
	
	public long getWriter() {
		return writer;
	}
	
	public String getWriterName() {
		return writerName;
	}
	
	public String getContent() {
		return content;
	}
	
	public LocalDateTime getWrittenDate() {
		return writtenDate;
	}
	
	public List<ReplyVo> getChildReplies() {
		return childReplies;
	}
	
}
