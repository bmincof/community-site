package dto;

public class LoginUserDto {

	private long userId;
	private String email;
	private String nickname;
	private boolean isAdmin = false;
	
	public LoginUserDto(long userId, String email, String nickname) {
		this.userId = userId;
		this.email = email;
		this.nickname = nickname;
	}
	
	public LoginUserDto(long userId, String email, String nickname, boolean isAdmin) {
		this.userId = userId;
		this.email = email;
		this.nickname = nickname;
		this.isAdmin = isAdmin;
	}
	
	public long getUserId() {
		return userId;
	}

	public String getEmail() {
		return email;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public boolean getIsAdmin() {
		return isAdmin;
	}
	
}
