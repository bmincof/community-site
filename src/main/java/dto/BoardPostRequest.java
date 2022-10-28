package dto;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class BoardPostRequest {

	@NotBlank
	@Size(min=2)
	private String title;
	
	@NotBlank
	private String content;
	
	private Integer type;
	
	private Boolean isNotice;
	
	//setter
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}
	
	public void setIsNotice(Boolean isNotice) {
		this.isNotice = isNotice;
	}
	
	//getter
	public String getTitle() {
		return title;
	}
	
	public String getContent() {
		return content;
	}
	
	public Integer getType() {
		return type;
	}
	
	public Boolean getIsNotice() {
		return isNotice;
	}
	
}