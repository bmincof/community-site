package dto;

public class BoardVotesDto {

	private Integer up;
	private Integer down;
	
	//setter
	public void setUp(Integer up) {
		this.up = up;
	}
	
	public void setDown(Integer down) {
		this.down = down;
	}
	
	//getter
	public Integer getUp() {
		return up;
	}
	
	public Integer getDown() {
		return down;
	}
	
	//other logics
	
	//선택한 boardVotesDto가 null이 아닐 때 투표 가능한지 확인
	public boolean isAbleToVote() {
		return (up == 0 && down == 0);
	}
	
}
