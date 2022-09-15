package dto;

public class BoardPostRequest {

	private String title;
	private String content;
	private boolean isNotice;
	
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