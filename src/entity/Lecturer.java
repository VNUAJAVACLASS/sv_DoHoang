package entity;

import java.util.Scanner;

public class Lecturer extends Human{
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Lecturer() {
	}
	
	public Lecturer(String code, String fullname) {
		super(code, fullname);
	}
	
	public Lecturer(String code, String fullname, String address) {
		super(code, fullname, address);
	}
	
	public Lecturer(String code, String fullname, String address, String password) {
		super(code, fullname, address);
		this.password = password;
	}
	
	@Override
	public void enterInfo(Scanner sc) {
		System.out.println("Nhap vao ten: ");
		this.fullname = sc.nextLine();
		System.out.println("Nhap vao ma code: ");
		this.code = sc.nextLine();
		System.out.println("Nhap vao dia chi: ");
		this.address = sc.nextLine();
		System.out.println("Nhap vao password: ");
		this.password = sc.nextLine();
	}

	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("============================\n");
	    sb.append("Ho ten       : ").append(fullname).append("\n");
	    sb.append("Ma giang vien: ").append(code).append("\n");
	    sb.append("Đia chi      : ").append(address).append("\n");
	    sb.append("Password     : ").append(password).append("\n");
		return sb.toString();
	}
}