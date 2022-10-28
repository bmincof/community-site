package vo;

public class PageVo {
	//생성자에서 setter를 쓰는게 맞나?
	private Integer totalPost;
	private Integer postPerPage = 10;
	private Integer curPage = 1;
	private Integer totPage;		// count / postPerPage
	// 하단 페이지 선택 바에 나오는 값들
	private Integer startPage;
	private Integer endPage;
	private Boolean isStartPage;
	private Boolean isLastPage;
	
	public PageVo(Integer totalPost, Integer curPage){
		this.totalPost = totalPost;
		this.curPage = curPage;
		calcTotalPage(totalPost, postPerPage);
		calcStartEndPage(curPage, totPage);
		this.isStartPage = checkIsStartPage();
		this.isLastPage = checkIsLastPage();
	}
	
	public void calcTotalPage(Integer totalPost, Integer postPerPage) {
		this.totPage = ((int) Math.ceil((double) totalPost / postPerPage));
	}
	
	public void calcStartEndPage(Integer curPage, Integer totPage) {
		int startPage = (curPage-1) / 10 * 10 + 1;
		int endPage = startPage + 9;
		if (endPage > totPage) {
			endPage = totPage;
		}
		this.startPage = startPage;
		this.endPage = endPage;
	}
	//logic
	public Boolean checkIsStartPage() {
		return startPage == 1 ? true : false;
	}
	
	public Boolean checkIsLastPage() {
		return endPage == totPage ? true : false;
	}
	
	//getter
	public Integer getTotalPost() {
		return totalPost;
	}
	
	public Integer getPostPerPage() {
		return postPerPage;
	}
	
	public Integer getCurPage() {
		return curPage;
	}
	
	public Integer getTotPage() {
		return totPage;
	}
	
	public Integer getStartPage() {
		return startPage;
	}
	
	public Integer getEndPage() {
		return endPage;
	}
	
	public Boolean getIsStartPage() {
		return isStartPage;
	}
	
	public Boolean getIsLastPage() {
		return isLastPage;
	}
	
}