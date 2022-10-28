package entity;

import java.time.LocalDateTime;

/**
 * 게시판 엔티티 클래스
 * User와 Board는 1:N 관계이다.
 * 
 * @author a
 *
 */

public class Board {

	private Long boardId;
	private String title;
	private String content;
	private Long writer;
	private LocalDateTime writtenDate;
	private Integer views;
	private Integer type;
	private Boolean isNotice;
	
	public Board(String title, String content, Long writer,
				LocalDateTime writtenDate, Integer type, Boolean isNotice) {
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.writtenDate = writtenDate;
		this.views = 0;
		this.type = type;
		this.isNotice = isNotice;
	}
	
	//setter
	public void setBoardId(Long boardId) {
		this.boardId = boardId;
	}
	
	//getter
	public Long getBoardId() {
		return boardId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getContent() {
		return content;
	}
	
	public Long getWriter() {
		return writer;
	}
	
	public LocalDateTime getWrittenDate() {
		return writtenDate;
	}
	
	public Boolean getIsNotice() {
		return isNotice;
	}
	
	public Integer getViews() {
		return views;
	}
	
	public Integer getType() {
		return type;
	}
	
}
