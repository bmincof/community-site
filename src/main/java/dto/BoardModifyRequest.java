package dto;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 댓글을 수정하기 위해 필요한 정보들을 담은 객체
 * 
 * @author a
 *
 */

public class BoardModifyRequest {

	private Long boardId;
	
	private String title;
	
	@NotBlank
	@Size(min=2)
	private String newTitle;
	
	private String content;
	
	@NotBlank
	private String newContent;
	
	private Integer type;
	
	public void setBoardId(Long boardId) {
		this.boardId = boardId;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setNewTitle(String newTitle) {
		this.newTitle = newTitle;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public void setNewContent(String newContent) {
		this.newContent = newContent;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}
	
	public Long getBoardId() {
		return boardId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getNewTitle() {
		return newTitle;
	}
	
	public String getContent() {
		return content;
	}
	
	public String getNewContent() {
		return newContent;
	}
	
	public Integer getType() {
		return type;
	}
	
}