package dto;

import org.hibernate.validator.constraints.NotBlank;

public class BoardPostRequest {

	@NotBlank
	private String title;
	
	@NotBlank
	private String content;
	
	private Boolean isNotice;
	
	//setter
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public void setIsNotice(boolean isNotice) {
		this.isNotice = isNotice;
	}
	
	//getter
	public String getTitle() {
		return title;
	}
	
	public String getContent() {
		return content;
	}
	
	public boolean getIsNotice() {
		return isNotice;
	}
	
}