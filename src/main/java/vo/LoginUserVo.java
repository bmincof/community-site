package vo;

/**
 * 로그인 성공 시 세션에 저장할 회원정보를 담을 객체
 * 
 * @author a
 *
 */

public class LoginUserVo {

	private Long userId;
	private String email;
	private String nickname;
	private Boolean isAdmin = false;
	
	public LoginUserVo(Long userId, String email, String nickname) {
		this.userId = userId;
		this.email = email;
		this.nickname = nickname;
	}
	
	public LoginUserVo(Long userId, String email, String nickname, Boolean isAdmin) {
		this.userId = userId;
		this.email = email;
		this.nickname = nickname;
		this.isAdmin = isAdmin;
	}
	
	public Long getUserId() {
		return userId;
	}

	public String getEmail() {
		return email;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public Boolean getIsAdmin() {
		return isAdmin;
	}
	
}