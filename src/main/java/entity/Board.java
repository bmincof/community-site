package entity;

import java.time.LocalDateTime;

public class Board {

	private long boardId;
	private String title;
	private String content;
	// 1:N (userId)
	private long writer;
	private LocalDateTime writtenDate;
	//private Comment comment;
	private int views = 0;
	private int upVotes = 0;
	private int downVotes = 0;
	private int type;
	
	public Board(String title, String content, long writer,
				LocalDateTime writtenDate) {
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.writtenDate = writtenDate;
	}
	
	//setter
	public void setBoardId(long boardId) {
		this.boardId = boardId;
	}
	
	//getter
	public long getBoardId() {
		return boardId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getContent() {
		return content;
	}
	
	public long getWriter() {
		return writer;
	}
	
	public LocalDateTime getWrittenDate() {
		return writtenDate;
	}
	
//	public Comment getComment() {
//		return comment;
//	}
	
	public int getUpVotes() {
		return upVotes;
	}
	
	public int getDownVotes() {
		return downVotes;
	}
	
	public int getViews() {
		return views;
	}
	
	public int getType() {
		return type;
	}
	
	//
	public void addViews() {
		this.views++;
	}
	
	public void addUpVotes() {
		this.upVotes++;
	}
	
	public void addDownVotes() {
		this.downVotes++;
	}
	
}
