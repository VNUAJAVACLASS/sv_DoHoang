package credit;

import java.util.Scanner;

public class Lecturer extends Human{
	private String password;
	
	public Lecturer() {
		
	}
	
	public Lecturer(String code, String password) {
		this.code=code;
		this.password=password;
	}
	
	public Lecturer(String code, String fullname, String address) {
		super(code,fullname,address);
	}
	
	@Override
	public void enterInfo(Scanner sc) {
		super.enterInfo(sc);
		System.out.println("Nhap mat khau: ");
		password=sc.nextLine();
	}
}