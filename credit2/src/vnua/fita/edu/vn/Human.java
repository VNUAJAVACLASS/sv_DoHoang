package vnua.fita.edu.vn;

import java.util.Scanner;

public abstract class Human {
	String address;
	String code;
	String fullname;

	public Human() {
		this.code = "xxxxxxxxxxxxxxxxxxxxxxxx";
	}

	public Human(String code) {
		this.code = code;
	}

	public Human(String code, String fullname) {
		this.code = code;
		this.fullname = fullname;
	}

	public Human(String address, String code, String fullname) {
		this.address = address;
		this.code = code;
		this.fullname = fullname;
	}

	// Update abstract
	public abstract void enterInfo(Scanner sc);

	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
		
}