package dto;

import java.time.LocalDateTime;

public class BoardDto {

	private long boardId;
	private String title;
	private String content;
	private long writer;
	private String writerName;
	private LocalDateTime writtenDate;
	private int views;
	private int upVotes;
	private int downVotes;
	private int type;
	
	//setter
	public void setBoardId(long boardId) {
		this.boardId = boardId;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public void setWriter(long writer) {
		this.writer = writer;
	}
	
	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}
	
	public void setWrittenDate(LocalDateTime writtenDate) {
		this.writtenDate = writtenDate;
	}
	
	public void setViews(int views) {
		this.views = views;
	}
	
	public void setUpVotes(int upVotes) {
		this.upVotes = upVotes;
	}
	
	public void setDownVotes(int downVotes) {
		this.downVotes = downVotes;
	}
	
	public void setType(int type) {
		this.type = type;
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
	
	public String getWriterName() {
		return writerName;
	}
	
	public LocalDateTime getWrittenDate() {
		return writtenDate;
	}
	
	public int getViews() {
		return views;
	}
	
	public int getUpVotes() {
		return upVotes;
	}
	
	public int getDownVotes() {
		return downVotes;
	}
	
	public int getType() {
		return type;
	}
}
