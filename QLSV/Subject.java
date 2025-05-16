package QLSV;


public class Subject {
	private int subjectID;
	private String subjectName;
	private int credit;
	private int heSo1;
	private int heSo2;
	private int heSo3;
	private int heSo4;
	private int heSo5;
	
	public Subject() {}
	
	public Subject(String subjectName, int credit, int heSo1, int heSo2, int heSo3, int heSo4, int heSo5) {
		this.subjectName = subjectName;
		this.credit = credit;
		this.heSo1 = heSo1;
		this.heSo2 = heSo2;
		this.heSo3 = heSo3;
		this.heSo4 = heSo4;
		this.heSo5 = heSo5;
	}
	
	public Subject(int subjectID, String subjectName, int credit, int heSo1, int heSo2, int heSo3, int heSo4, int heSo5) {
		this.subjectID = subjectID;
		this.subjectName = subjectName;
		this.credit = credit;
		this.heSo1 = heSo1;
		this.heSo2 = heSo2;
		this.heSo3 = heSo3;
		this.heSo4 = heSo4;
		this.heSo5 = heSo5;
	}
	
	public int getsubjectID() {
		return subjectID;
	}
	
	public void setsubjectID(int subjectID) {
		this.subjectID = subjectID;
	}
	
	public String getsubjectName() {
		return subjectName;
	}
	
	public void setsubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	public int getcredit() {
		return credit;
	}
	
	public void setcredit(int credit) {
		this.credit = credit;
	}
	
	public int getheSo1() {
		return heSo1;
	}
	
	public void setheSo1(int heSo1) {
		this.heSo1 = heSo1;
	}
	
	public int getheSo2() {
		return heSo2;
	}
	
	public void setheSo2(int heSo2) {
		this.heSo2 = heSo2;
	}
	
	public int getheSo3() {
		return heSo3;
	}
	
	public void setheSo3(int heSo3) {
		this.heSo3 = heSo3;
	}
	
	public int getheSo4() {
		return heSo4;
	}
	
	public void setheSo4(int heSo4) {
		this.heSo4 = heSo4;
	}
	
	public int getheSo5() {
		return heSo5;
	}
	
	public void setheSo5(int heSo5) {
		this.heSo5 = heSo5;
	}
	
}