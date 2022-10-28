package dto;

import java.time.LocalDateTime;

public class UserDto {

	private Long userId;	// serial number
	private String email; // for login
	private String password;
	private String name;
	private String nickname;
	private String phoneNumber;
	private LocalDateTime registerDate;

	//userId
	public Long getUserId() {
		return userId;
	}

	//email
	public String getEmail() {
		return email;
	}
	
	//password
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	//name
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	//nickname
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	//phoneNumber
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	//registerDate
	public LocalDateTime getRegisterDate() {
		return registerDate;
	}
	
	public void setRegisterDate(LocalDateTime registerDate) {
		this.registerDate = registerDate;
	}
	
}
