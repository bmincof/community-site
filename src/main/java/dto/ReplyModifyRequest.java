package dto;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.NotBlank;

public class ReplyModifyRequest {

	private Long replyId;
	private Long boardId;
	@NotBlank
	private String content;
	private String writerName;
	private LocalDateTime writtenDate;
	
	public void setReplyId(Long replyId) {
		this.replyId = replyId;
	}
	
	public void setBoardId(Long boardId) {
		this.boardId = boardId;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}
	
	public void setWrittenDate(LocalDateTime writtenDate) {
		this.writtenDate = writtenDate;
	}
	
	public Long getReplyId() {
		return replyId;
	}

	public Long getBoardId() {
		return boardId;
	}
	
	public String getContent() {
		return content;
	}
	
	public String getWriterName() {
		return writerName;
	}
	
	public LocalDateTime getWrittenDate() {
		return writtenDate;
	}
	
}