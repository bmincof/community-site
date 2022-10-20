package dto;

/**
 * 회원 로그인 시 필요한 값들을 담은 객체
 * 
 * @author a
 *
 */

public class UserLoginRequest {

	private String email;
	private String password;
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
}