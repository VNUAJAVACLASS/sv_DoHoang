package service;

import java.util.List;

import dao.SubjectDAO;
import entity.Subject;

public class SubjectService {
	private SubjectDAO subjectDAO;
	
	public SubjectService() {
		subjectDAO = new SubjectDAO();
	}
	
	public String printAllSubject() {
		List<Subject> listSubject = subjectDAO.getAllSubject();
		if(listSubject.isEmpty())
			return "Không tìm thấy";
		return listSubject.toString();
	}
	
	public String searchSubject(String code) {
		Subject sub = subjectDAO.getSubjectBySubjectCode(code);
		if(sub.equals(null))
			return "Không tìm thấy môn học";
		return sub.toString();
	}
	
	public String addSubject(Subject sub) {
		if(subjectDAO.addSubject(sub))
			return "Thêm thành công môn học";
		return "Thêm môn học thất bại";
	}
	
	public String deleteSubject(String subjectCode) {
		if(subjectDAO.deleteSubjectByCode(subjectCode))
			return "Xóa thành công môn học";
		return "Xóa thất bại";
	}
	
	public String updateSubject(Subject subject) {
		if(subjectDAO.updateSubject(subject))
			return "Cap nhat thanh cong mon hoc";
		return "cap nhat that bai mon hoc";
	}
}