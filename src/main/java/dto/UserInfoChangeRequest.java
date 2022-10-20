package dto;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class UserInfoChangeRequest {

	private Long userId;
	
	@Size(min=8)
	@NotBlank
	private String newPassword;
	
	@NotBlank
	private String newNickname;
	
	@NotBlank
	private String newPhoneNumber;
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
		this.newNickname = "-";
		this.newPhoneNumber = "-";
	}
	
	public void setNewNickname(String newNickname) {
		this.newNickname = newNickname;
		this.newPassword = "--------";
		this.newPhoneNumber = "-";
	}
	
	public void setNewPhoneNumber(String newPhoneNumber) {
		this.newPhoneNumber = newPhoneNumber;
		this.newPassword = "--------";
		this.newNickname = "-";
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public String getNewPassword() {
		return newPassword;
	}
	
	public String getNewNickname() {
		return newNickname;
	}
	
	public String getNewPhoneNumber() {
		return newPhoneNumber;
	}
	
}