package credit1;

import java.util.Scanner;

public abstract class Human {
	protected String address;
	protected String code;
	protected String fullname;
	
	public Human() {}
	
	public Human(String code) {
		this.code = code;
	}
	
	public Human(String code, String fullname) {
		this.code = code;
		this.fullname = fullname;
	}
	
	public Human(String code, String fullnam, String address) {
		this(code, fullnam);
		this.address = address;
	}
	
	public abstract void enterInfo(Scanner sc); 
	
	@Override
	public boolean equals(Object obj) {
		Human anotherHuman = (Human)obj;
		return this.code.equals(anotherHuman.code);
	}

}