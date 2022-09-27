package dto;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 회원가입 시 필요한 정보들을 담고있는 클래스
 * 
 * @author a
 *
 */

public class UserRegisterRequest {
	
	@Email
	@NotBlank
	private String email;

	@Min(8)
	@NotBlank
	private String password;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String nickname;
	
	@NotBlank
	private String phoneNumber;
	
	private Boolean isAdmin=false;			
	
	//setter
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	//getter
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getName() {
		return name;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public boolean getIsAdmin() {
		return isAdmin;
	}
	
}