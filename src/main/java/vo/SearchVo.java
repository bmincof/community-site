package vo;

/**
 * 게시판 내 검색에 필요한 정보들을 모아 놓은 객체
 * 
 * @author a
 *
 */

public class SearchVo {

	private String field;
	private String keyword;
	
	public SearchVo(String field, String keyword) {
		this.field = field;
		this.keyword = keyword;
	}
	
	public String getField() {
		return field;
	}
	
	public String getKeyword() {
		return keyword;
	}
	
}