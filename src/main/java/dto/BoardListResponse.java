package dto;

import java.util.List;

/**
 * 카테고리별 게시판에서 게시글 리스트를 보여주기 위해 필요한 정보들을 담은 객체
 * 
 * @author a
 *
 */

public class BoardListResponse {
	private Integer boardType;
	private List<BoardDto> notices;
	private List<BoardDto> hotPosts;
	private List<BoardDto> posts;
	
	public BoardListResponse() {
	}
	
	public BoardListResponse(Integer boardType) {
		this.boardType = boardType;
	}
	
	public void setNotices(List<BoardDto> notices) {
		this.notices = notices;
	}
	
	public void setHotPosts(List<BoardDto> hotPosts) {
		this.hotPosts = hotPosts;
	}
	
	public void setPosts(List<BoardDto> posts) {
		this.posts = posts;
	}
	
	public Integer getBoardType() {
		return boardType;
	}
	
	public List<BoardDto> getNotices() {
		return notices;
	}
	
	public List<BoardDto> getHotPosts() {
		return hotPosts;
	}
	
	public List<BoardDto> getPosts() {
		return posts;
	}
	
}