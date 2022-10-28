package entity;

import java.time.LocalDateTime;

/**
 * 게시판 사용자 엔티티 클래스
 * 
 * @author a
 *
 */

public class User {

	private long userId;
	private String email;
	private String password;
	private String name;
	private String nickname;
	private String phoneNumber;
	private LocalDateTime registerDate;
	private boolean isAdmin;
	private Boolean isDeleted;
	
	public User(String email, String password, String name, String nickname,
			String phoneNumber, LocalDateTime registerDate, boolean isAdmin, Boolean isDeleted) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.nickname = nickname;
		this.phoneNumber = phoneNumber;
		this.registerDate = registerDate;
		this.isAdmin = isAdmin;
		this.isDeleted = isDeleted;
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
	
	public LocalDateTime getRegisterDate() {
		return registerDate;
	}
	
	public boolean getIsAdmin() {
		return isAdmin;
	}
	
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	
	// other method
	
	public void changePassword(String newPassword) {
		this.password = newPassword;
	}
	
	public void changeNickname(String newNickname) {
		this.nickname = newNickname;
	}
	
	public void changePhoneNumber(String newPhoneNumber) {
		this.phoneNumber = newPhoneNumber;
	}
		
}
