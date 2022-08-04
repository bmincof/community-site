package dto;

import java.time.LocalDateTime;

public class UserDto {

	private long userId;	// serial number
	private String email; // for login
	private String password;
	private String name;
	private String nickname;
	private String phoneNumber;
	private LocalDateTime registerDate;

	//userId
	public long getUserId() {
		return userId;
	}
	
	public void setUserId(long userId) {
		this.userId = userId;
	}

	//email
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
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
