package dto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 댓글 정보를 뷰로 전달하기 위한 객체
 * 
 * @author a
 *
 */

public class ReplyDto {

	private Long replyId;
	private Long boardId;
	private Long writer;
	private String writerName;
	private String content;
	private LocalDateTime writtenDate;
	private List<ReplyDto> childReplies;
	private Boolean isDeleted;
	
	//setter
	public void setReplyId(Long replyId) {
		this.replyId = replyId;
	}
	
	public void setBoardId(Long boardId) {
		this.boardId = boardId;
	}
	
	public void setWriter(Long writer) {
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
	
	public void setChildReplies(List<ReplyDto> childReplies) {
		this.childReplies = childReplies;
	}
	
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	//getter
	public Long getReplyId() {
		return replyId;
	}
	
	public Long getBoardId() {
		return boardId;
	}
	
	public Long getWriter() {
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
	
	public List<ReplyDto> getChildReplies() {
		return childReplies;
	}
	
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	
}