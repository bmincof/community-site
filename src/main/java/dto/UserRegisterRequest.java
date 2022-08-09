package dto;

public class UserRegisterRequest {
	
	private String email;
	private String password;
	private String confirmPassword;
	private String name;
	private String nickname;
	private String phoneNumber;
	private String type;			//null -> 일반 유저 , admin -> 관리자
	
	public UserRegisterRequest() {};

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
	
	//confirmPassword
	public String getConfirmPassword() {
		return confirmPassword;
	}
	
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
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
	
	//type
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
		
	//other logic
	public boolean isPasswordEqualToConfirmPassword() {
		return password.equals(confirmPassword);
	}
	
}
