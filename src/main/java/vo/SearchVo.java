package vo;

public class SearchVo {

	private String field;
	private String keyword;
	
	public SearchVo(String field, String keyword) {
		this.field = field;
		this.keyword = keyword;
	}
	
	
	public void setField(String field) {
		this.field = field;
	}
	
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public String getField() {
		return field;
	}
	
	public String getKeyword() {
		return keyword;
	}
	
}