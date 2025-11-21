package model;

public class User {
	private String userName;
	private String password;
	private String fullname;
	private String email;
	private String mobile;
	private String address;
	private int role;

	public User() {
	}

	public User(String userName, String password, String fullname, String email, String mobile, String address) {
		this.userName = userName;
		this.password = password;
		this.fullname = fullname;
		this.email = email;
		this.mobile = mobile;
		this.address = address;
		this.role = 0; // mặc định user
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}
}
