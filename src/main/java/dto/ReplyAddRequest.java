package dto;

import org.hibernate.validator.constraints.NotBlank;

public class ReplyAddRequest {

	private Long boardId;
	private Long writer;
	private Long ref;
	@NotBlank
	private String content;
	
	public void setBoardId(Long boardId) {
		this.boardId = boardId;
	}
	
	public void setWriter(Long writer) {
		this.writer = writer;
	}
	
	public void setRef(Long ref) {
		this.ref = ref;
	}
	
	public void setContent(String content) {
		this.content = content;
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
	
}
