package entity;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Student extends Human {
	private String class_;
	private Map<String,Subject> subjectList = new HashMap<String,Subject>();
	
	public Student(){
		
	}
	
	public Student(String code){
		super(code);
	}
	
	public Student(String code, String fullname){
		super(code, fullname);
	}
	
	public Student(String code, String fullname, String class_){
		super(code, fullname);
		this.class_ = class_;
	}

	public Student(String code, String fullname, String class_, String address){
		super(code, fullname, address);
		this.class_ = class_;
	}

	public void addSubject(Subject sub) {
		subjectList.put(sub.getSubjectCode(), sub);
	}
	
	public void removeSubject(String key) {
		subjectList.remove(key);
	}
	
	public String getClass_() {
		return class_;
	}

	public void setClass_(String class_) {
		this.class_ = class_;
	}
	
	public List<Subject> searchSubject(String key) {
		List<Subject> listResult = new LinkedList<Subject>();
	    subjectList.forEach( (k, v) -> {
	    	if (v.getSubjectName().contains(key)) {
				listResult.add(v);
			} } );
		return listResult;
	}
	
	public float calTermAverageMark() {
	    int sumCredit = 0;
	    int sumGrade = 0;
	    for (HashMap.Entry<String, Subject> entry : subjectList.entrySet()) {
	        Subject subject = entry.getValue();
	        sumCredit += subject.getCredit();
	        sumGrade += subject.calConversionMark(subject.calGrade()) * subject.getCredit();
	    }
	    return (float) sumGrade / sumCredit;
	}
	
	@Override
	public void enterInfo(Scanner sc) {
		System.out.println("Nhap vao ten: ");
		this.fullname = sc.nextLine();
		System.out.println("Nhap vao ma code: ");
		this.code = sc.nextLine();
		System.out.println("Nhap vao dia chi: ");
		this.address = sc.nextLine();
		System.out.println("Nhap vao lop: ");
		this.class_ = sc.nextLine();
	}

	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("========================================\n");
	    sb.append("Ho ten       : ").append(fullname).append("\n");
	    sb.append("Ma sinh vien : ").append(code).append("\n");
	    sb.append("Lop          : ").append(class_).append("\n");
	    sb.append("Đia chi      : ").append(address).append("\n");
	    if (subjectList.isEmpty()) {
		    sb.append("Sinh vien khong co mon hoc nao trong ky\n");
		    return sb.toString();
	    }
	    sb.append("Danh sach mon hoc:\n");
	    for (Subject s : subjectList.values()) {
	        sb.append(s).append("\n");
	    }	
	    sb.append("Diem trung binh hoc ky: ").append(calTermAverageMark()).append("\n");
	    return sb.toString();
	}

}