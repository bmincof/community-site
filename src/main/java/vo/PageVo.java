package vo;

public class PageVo {
	//생성자에서 setter를 쓰는게 맞나?
	private int totalPost;
	private int postPerPage = 10;
	private int curPage = 1;
	private int totPage;		// count / postPerPage
	// 하단 페이지 선택 바에 나오는 값들
	private int startPage;
	private int endPage;
	private boolean isStartPage;
	private boolean isLastPage;
	
	public PageVo(int totalPost, int curPage){
		this.totalPost = totalPost;
		this.curPage = curPage;
		calcTotalPage(totalPost, postPerPage);
		calcStartEndPage(curPage, totPage);
		this.isStartPage = checkIsStartPage();
		this.isLastPage = checkIsLastPage();
	}
	
	public void calcTotalPage(int totalPost, int postPerPage) {
		setTotalPage((int) Math.ceil((double) totalPost / postPerPage));
	}
	
	public void calcStartEndPage(int curPage, int totPage) {
		int startPage = (curPage-1) / 10 * 10 + 1;
		int endPage = startPage + 9;
		if (endPage > totPage) {
			endPage = totPage;
		}
		setStartPage(startPage);
		setEndPage(endPage);
	}
	//logic
	public boolean checkIsStartPage() {
		return startPage == 1 ? true : false;
	}
	
	public boolean checkIsLastPage() {
		return endPage == totPage ? true : false;
	}
	
	//setter
	public void setTotalPost(int totalPost) {
		this.totalPost = totalPost;
	}
	
	public void setTotalPage(int totPage) {
		this.totPage = totPage;
	}
	
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
	public void setIsStartPage(boolean isStartPage) {
		this.isStartPage = isStartPage;
	}
	
	public void setIsLastPage(boolean isLastPage) {
		this.isLastPage = isLastPage;
	}
	
	//getter
	public int getTotalPost() {
		return totalPost;
	}
	
	public int getPostPerPage() {
		return postPerPage;
	}
	
	public int getCurPage() {
		return curPage;
	}
	
	public int getTotPage() {
		return totPage;
	}
	
	public int getStartPage() {
		return startPage;
	}
	
	public int getEndPage() {
		return endPage;
	}
	
	public boolean getIsStartPage() {
		return isStartPage;
	}
	
	public boolean getIsLastPage() {
		return isLastPage;
	}
}
