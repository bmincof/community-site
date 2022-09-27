package entity;

/**
 * 게시글의 추천, 비추천, 북마크 정보를 담고있는 엔티티 클래스
 * Board와 BoardVotes는 1:N 관계이다.
 * 
 * @author a
 *
 */

public class BoardVotes {

	private long boardId;
	private long userId;
	private int up;
	private int down;
	
	public BoardVotes(long boardId, long userId, int up, int down) {
		this.boardId = boardId;
		this.userId = userId;
		this.up = up;
		this.down = down;
	}
	
	//getter
	public long getBoardId() {
		return boardId;
	}
	
	public long getUserId() {
		return userId;
	}
	
	public int getUp() {
		return up;
	}
	
	public int getDown() {
		return down;
	}
	
}
