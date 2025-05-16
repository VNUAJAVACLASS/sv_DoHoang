package vnua.fita.edu.vn;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HRM {
	private List<Human> hrList;

	public HRM() {
		hrList = new ArrayList<Human>();
	}

	public void addHR(Human hr) {
		hrList.add(hr);
	}

	public void addHR(Scanner sc) {
		int loai;
		System.out.println("Chon loai ns(sv:0 ,gv: 1): ");
		loai = sc.nextInt(); sc.nextLine();

		Human human = null;
		if (loai == 0) {
			human = new Student();
		} else if (loai == 1) {
			human = new Lecturer();
		}

		human.enterInfo(sc);
		addHR(human);
	}

	public void printHRList() {
		for (Human human : hrList) {
			System.out.println(human.toString());
		}
	}

	public void printStudentList() {
		for (Human human : hrList) {
			if (human instanceof Student) {
				Student std = (Student) human;
				System.out.println(std.getClass_());
			}
		}
	}

	public void initDemoData() {
		Human h1 = new Student("686543", "Nguyen Duc Hoan", "K68Cnttc");
		Human h2 = new Lecturer("cnpm11", "Nguyen Anh Duc", "HA NOI");
		Human h3 = new Student("687639", "Tran Anh Van", "K68CMAPA");

		addHR(h1);
		addHR(h2);
		addHR(h3);
	}

	public List<Human> searchHuman(String code) {
		List<Human> humanlist = new ArrayList<Human>();
		for (Human human : hrList) {
			if (human.code.contains(code)) {
				humanlist.add(human);
			}
		}

		return humanlist;
	}

	public static void main(String[] args) {
		HRM hrm = new HRM();
		hrm.initDemoData();
		hrm.printHRList();
		hrm.searchHuman("");
	}

}