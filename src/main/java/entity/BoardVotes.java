package entity;

/**
 * 게시글의 추천, 비추천, 북마크 정보를 담고있는 엔티티 클래스
 * Board와 BoardVotes는 1:N 관계이다.
 * 
 * @author a
 *
 */

public class BoardVotes {

	private Long boardId;
	private Long userId;
	private Integer up;
	private Integer down;
	
	public BoardVotes(Long boardId, Long userId, Integer up, Integer down) {
		this.boardId = boardId;
		this.userId = userId;
		this.up = up;
		this.down = down;
	}
	
	//getter
	public Long getBoardId() {
		return boardId;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public Integer getUp() {
		return up;
	}
	
	public Integer getDown() {
		return down;
	}
	
}