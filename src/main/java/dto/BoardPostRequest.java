package dto;

public class BoardPostRequest {

	private String title;
	private String content;
	
	//setter
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	//getter
	public String getTitle() {
		return title;
	}
	
	public String getContent() {
		return content;
	}
	
}