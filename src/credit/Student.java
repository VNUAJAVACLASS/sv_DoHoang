package credit;

import java.util.ArrayList;
import java.util.List;

public class Student extends Human {
	private String class_;
	private List<Subject> subjectlist = new ArrayList<Subject>();

	public Student() {

	}

	public Student(String code) {
		this.code = code;
	}

	public Student(String code, String fullname) {
		super(code, fullname);
	}

	public Student(String code, String fullname, String class_) {
		super(code, fullname);
		this.class_ = class_;
	}

	public Student(String code, String fullname, String class_, String address) {
		this(code, fullname, class_);
		this.address = address;
	}

	public void addSubject(Subject sub) {
		subjectlist.add(sub);
	}

	public float calTermAverageMark() {
		float ts = 0;
		int ms = 0;
		for (Subject sub : subjectlist) {
			ts += sub.getCredit() * sub.calConversionMark();
			ms += sub.getCredit();
		}

		return ts / ms;
	}

	@Override
	public String toString() {
		return code + "-" + fullname + "-" + class_;
	}

	@Override
	public boolean equals(Object obj) {
		// 2 sv la tuong duong ve hoc luc neu tri tuyet doi hieu so diem TBHK <0.3
		Student antherstd = (Student) obj;
		float d = Math.abs(this.calTermAverageMark() - antherstd.calTermAverageMark());
		return d < 0.3;
	}

	public String getClass_() {
		return class_;
	}

	public void setClass_(String class_) {
		this.class_ = class_;
	}

	public static void main(String[] args) {
		Student std = new Student("687629", "Long Ca", "K34796C");

		Subject sub1 = new Subject("TH03111", "Lap trinh Java", 3);
		sub1.setAttendanceMark(8.5f);
		sub1.setMidExamMark(6.8f);
		sub1.setFinalExamMark(7.6f);

		Subject sub2 = new Subject("TH0007", "Tin co so", 3);
		sub2.setAttendanceMark(9);
		sub2.setMidExamMark(10);
		sub2.setFinalExamMark(7.5f);

		std.addSubject(sub1);
		std.addSubject(sub2);

		System.out.println("TBHK: " + std.calTermAverageMark());
	}

}