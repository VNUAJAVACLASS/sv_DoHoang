package service;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import dao.UserDAO;
import dao.SubjectDAO;
import entity.Human;
import entity.Lecturer;
import entity.Student;
import entity.Subject;

public class UserService {
	private UserDAO userDAO;
	private SubjectDAO subjectDAO;

	public UserService() {
		userDAO = new UserDAO();
		subjectDAO = new SubjectDAO();
	}
	
	public String addHR(Scanner sc) {
        System.out.print("Nhap loai nhan su (1 - Lecturer, 2 - Student): ");
        int type = sc.nextInt();
        sc.nextLine();
        
        Human hm;
        if (type == 1) {
            hm = new Lecturer();
            hm.enterInfo(sc);
        } else if (type == 2) {
            hm = new Student();
            hm.enterInfo(sc);
        } else {
        	return "Loai nhan su khong hop le!";
        }
        if(userDAO.addHuman(hm))
        	return "Thêm nhân sự thành công";
		return "Thêm nhân sự thất bại";
	}
	
	public String updateHuman(Scanner sc) {
	    System.out.print("Nhap ma nhan su can cap nhat: ");
	    String code = sc.nextLine();
	    Human old = userDAO.getHumanByCode(code);
	    if (old == null) {
	        return "Khong tim thay nhan su.";
	    }
	    System.out.println("Nhap thong tin moi:");
	    if (old instanceof Lecturer) {
	        old = new Lecturer();
	    } else {
	        old = new Student();
	    }
	    old.enterInfo(sc);
	    old.setCode(code);
	    if (userDAO.updateHuman(old))
	        return "Cap nhat thanh cong.";
	    return "Cap nhat that bai.";
}

	public String deleteHuman(Scanner sc) {
	    System.out.print("Nhap ma nhan su can xoa: ");
	    String code = sc.nextLine();
	    if (userDAO.deleteHumanByCode(code))
	        return "Xoa thanh cong.";
	    return "Xoa that bai.";
	}

	public String printHRList() {
		List<Human> hrList = new LinkedList<Human>();
		System.out.println("Danh sach nhan su la:");
		hrList = userDAO.getAllHuman();
	    if(hrList.isEmpty())
	    	return "Không có nhân sự nào";
	    return hrList.toString();
	}
	
	public String printLecturer() {
		List<Human> hrList = new LinkedList<Human>();
		System.out.println("Danh sach giang vien la:");
		hrList = userDAO.getAllLecturer();
	    if(hrList.isEmpty())
	    	return "Không có nhân sự là giảng viên";
	    return hrList.toString();
	}
	
	public String printStudent() {
		List<Human> hrList = new LinkedList<Human>();
		System.out.println("Danh sach sinh vien la:");
		hrList = userDAO.getAllStudent();
	    if(hrList.isEmpty())
	    	return "Không có nhân sự là sinh viên";
	    return hrList.toString();
	}
	
	public String searchHuman(String code) {
		Human hm = userDAO.getHumanByCode(code);
		if (hm == null) {
			return "Không tìm thấy";
		}

		return hm.toString();
	}
	
	public String registerSubject(String studentCode, String subjectCode) {
		Student student = userDAO.getStudentByCode(subjectCode);
		if(student==null)
			return "Không tìm thấy sinh viên";
		Subject subject = subjectDAO.getSubjectBySubjectCode(subjectCode);
		if(subject==null)
			return "Không tìm môn học";
		if(userDAO.addSubjectForStudent(student, subject))
			return "Thêm môn học cho sinh viên thất bại";
		return "Thêm môn học cho sinh viên thành công";
	}
	
	public String addScore(String studentCode, String subjectCode) {
		Student student = userDAO.getStudentByCode(subjectCode);
		if(student==null)
			return "Không tìm thấy sinh viên";
		System.out.println("Chon mon hoc muon dang ky: ");
		Subject subject = subjectDAO.getSubjectBySubjectCode(subjectCode);
		if(subject==null)
			return "Không tìm môn học";
		if(userDAO.addSubjectForStudent(student, subject))
			return "Thêm môn học cho sinh viên thất bại";
		return "Thêm môn học cho sinh viên thành công";
	}
}