package credit;

import java.util.Scanner;

public class Human {
	protected String address;
	protected String code;
	protected  String fullname;
	
	public Human() {
		
	}
	
	public Human(String code) {
		this.code=code;
	}
	
	public Human(String code, String fullname) {
		this.code=code;
		this.fullname=fullname;
	}
	
	public Human(String code, String fullname, String address) {
		this(code,fullname);
		this.address=address;
	}
	
	public void enterInfo(Scanner sc) {
		System.out.println("Nhap ma: ");
		code=sc.nextLine();
		System.out.println("Nhap vao ho ten: ");
		fullname=sc.nextLine();
		System.out.println("Nhap vao dia chi: ");
		address=sc.nextLine();
	}
	
	@Override
	public String toString() {
		return code + "-" + fullname +"-" + address;
	}
	
	@Override
	public boolean equals(Object obj) {
		Human anotherHuman = (Human)obj;
		return this.code.equals(anotherHuman.code);
	}
}