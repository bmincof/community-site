package dto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 게시판 및 게시글 상세보기에서 게시글의 정보를 보여주기 위한 객체
 * 
 * @author a
 *
 */

public class BoardDto {

	private Long boardId;
	private String title;
	private String content;
	private Long writer;
	private String writerName;
	private LocalDateTime writtenDate;
	private Integer views;
	private BoardVotesDto boardVotesDto;
	private Integer type;
	private Boolean isNotice;
	private List<ReplyDto> replies;
	
	//setter
	public void setBoardId(Long boardId) {
		this.boardId = boardId;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public void setWriter(Long writer) {
		this.writer = writer;
	}
	
	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}
	
	public void setWrittenDate(LocalDateTime writtenDate) {
		this.writtenDate = writtenDate;
	}
	
	public void setViews(Integer views) {
		this.views = views;
	}
	
	public void setVotes(BoardVotesDto boardVotesDto) {
		this.boardVotesDto = boardVotesDto;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}
	
	public void setIsNotice(Boolean isNotice) {
		this.isNotice = isNotice;
	}
	
	public void setReplies(List<ReplyDto> replies) {
		this.replies = replies;
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
	
	public String getWriterName() {
		return writerName;
	}
	
	public LocalDateTime getWrittenDate() {
		return writtenDate;
	}
	
	public Integer getViews() {
		return views;
	}
	
	public BoardVotesDto getVotes() {
		return boardVotesDto;
	}
	
	public Integer getType() {
		return type;
	}
	
	public Boolean getIsNotice() {
		return isNotice;
	}
	
	public List<ReplyDto> getReplies() {
		return replies;
	}
	
}