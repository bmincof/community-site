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
	private int type;
	private boolean isNotice;
	
	public Board(String title, String content, long writer,
				LocalDateTime writtenDate, boolean isNotice) {
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.writtenDate = writtenDate;
		this.isNotice = isNotice;
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
	
	public boolean getIsNotice() {
		return isNotice;
	}
	
//	public Comment getComment() {
//		return comment;
//	}
	
	public int getViews() {
		return views;
	}
	
	public int getType() {
		return type;
	}
	
}
