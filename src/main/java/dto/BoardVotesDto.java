package dto;

public class BoardVotesDto {

	private int up;
	private int down;
	private int bookmark;
	
	//setter
	public void setUp(int up) {
		this.up = up;
	}
	
	public void setDown(int down) {
		this.down = down;
	}
	
	public void setBookmark(int bookmark) {
		this.bookmark = bookmark;
	}
	
	//getter
	public int getUp() {
		return up;
	}
	
	public int getDown() {
		return down;
	}
	
	public int getBookmark() {
		return bookmark;
	}
	
	//other logics
	
	//선택한 boardVotesDto가 null이 아닐 때 투표 가능한지 확인
	public boolean isAbleToVote() {
		return (up == 0 && down == 0);
	}
	
}
