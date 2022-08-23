package entity;

import java.time.LocalDateTime;
import java.util.List;

public class Comment {

	private long commentId;
	private String content;
	// 1:N
	private User writer;
	private Board post;
	private LocalDateTime writtenDate;
	private List<Comment> replies;
	private int upVotes = 0;
	private int downVotes = 0;
	
	public Comment(long commentId, String content, User writer,
				LocalDateTime writtenDate) {
		this.commentId = commentId;
		this.content = content;
		this.writer = writer;
		this.writtenDate = writtenDate;
	}
	
	//getter
	public long getCommentId() {
		return commentId;
	}
	
	public String getContent() {
		return content;
	}
	
	public User getUser() {
		return writer;
	}
	
	public Board getPost() {
		return post;
	}
	
	public LocalDateTime getWrittenDate() {
		return writtenDate;
	}
	
}
