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

	private long replyId;
	private long boardId;
	private long writer;
	private long ref;
	private String content;
	private LocalDateTime writtenDate;
	
	public Reply(long boardId, long writer, long ref,
			String content, LocalDateTime writtenDate) {
		this.boardId = boardId;
		this.writer = writer;
		this.ref = ref;
		this.content = content;
		this.writtenDate = writtenDate;
	}
	
	public void setReplyId(long replyId) {
		this.replyId = replyId;
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
	
	public long getRef() {
		return ref;
	}
	
	public String getContent() {
		return content;
	}
	
	public LocalDateTime getWrittenDate() {
		return writtenDate;
	}
	
}
