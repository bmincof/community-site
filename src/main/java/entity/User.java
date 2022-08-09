package entity;

import java.time.LocalDateTime;

public class User {

	private long userId;	// serial number
	private String email; // for login
	private String password;
	private String name;
	private String nickname;
	private String phoneNumber;
	private String type;
	private LocalDateTime registerDate;
	
	public User(String email, String password, String name, String nickname,
			String phoneNumber, String type, LocalDateTime registerDate) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.nickname = nickname;
		this.phoneNumber = phoneNumber;
		this.type = type;
		this.registerDate = registerDate;
	}
	
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public long getUserId() {
		return userId;
	}
	
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
	
	public String getType() {
		return type;
	}
	
	public LocalDateTime getRegisterDate() {
		return registerDate;
	}
	
		
}
