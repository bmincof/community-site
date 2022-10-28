package entity;

import java.time.LocalDateTime;

/**
 * 게시글 상세보기에 표시될 댓글 엔티티 클래스
 * Board와 Reply 는 1:N 관계이다.
 * 
 * @author a
 *
 */

public class Reply {

	private Long replyId;
	private Long boardId;
	private Long writer;
	private Long ref;
	private String content;
	private LocalDateTime writtenDate;
	
	public Reply(Long boardId, Long writer, Long ref,
			String content, LocalDateTime writtenDate) {
		this.boardId = boardId;
		this.writer = writer;
		this.ref = ref;
		this.content = content;
		this.writtenDate = writtenDate;
	}
	
	public void setReplyId(Long replyId) {
		this.replyId = replyId;
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
	
	public Long getRef() {
		return ref;
	}
	
	public String getContent() {
		return content;
	}
	
	public LocalDateTime getWrittenDate() {
		return writtenDate;
	}
	
}