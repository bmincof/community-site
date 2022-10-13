package dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 회원 정보를 찾기 위한 정보를 담은 객체
 * 
 * @author a
 *
 */

public class UserInfoFindRequest {

	@Email
	private String email;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String phoneNumber;
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
}
