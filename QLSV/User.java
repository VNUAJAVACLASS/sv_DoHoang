package QLSV;


public class User {
	private int userID;
	private String fullName;
	private Boolean gender;
	private String address;
	private String Password;
	private String userType;

	public User(String fullName, Boolean gender, String address, String PassWord, String userType) {
		this.fullName = fullName;
		this.gender = gender;
		this.address = address;
		this.Password = PassWord;
		this.userType = userType;
	}
	
	public User(int userID, String fullName, Boolean gender, String address, String PassWord, String userType) {
		this.userID = userID;
		this.fullName = fullName;
		this.gender = gender;
		this.address = address;
		this.Password = PassWord;
		this.userType = userType;
	}
	
	public int getUserID() {
		return userID;
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public String getfullName() {
		return fullName;
	}
	
	public void setfullname(String fullName) {
		this.fullName = fullName;
	}
	
	public Boolean getgender() {
		return gender;
	}
	
	public void setgender(Boolean gender) {
		this.gender = gender;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPassWord() {
		return Password;
	}
	
	public void setPassWord(String PassWord) {
		this.Password = PassWord;
	}
	
	public String getUsertype() {
		return userType;
	}
	
	public void setUsertype(String userType) {
		this.userType = userType;
	}
	
	
	@Override
	public String toString() {
	    return "UserID: " + userID + ", Full Name: " + fullName +
	           ", Gender: " + (gender ? "Nam" : "Nữ") ;
	}

}
