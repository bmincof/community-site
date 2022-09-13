package entity;

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
