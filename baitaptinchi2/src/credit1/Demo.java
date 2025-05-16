 package credit1;

public class Demo {
	public static void main(String[] args) {
		
    Student sv = new Student("SV01", "Nguyen Van A", "KTPM01", "Ha Noi");

    Subject java = new JavaSubject("JV01", "Java Programming", 3, 8, 7, 9);
    Subject python = new PythonSubject("PY01", "Python Programming", 4, 9, 8, 7, 9);

    sv.addSubject(java);
    sv.addSubject(python);

    System.out.println("Diem TB hoc phan JAVA: "+ java.calSubjectMark()+"-"+java.calConversionMark()+"-"+java.calGrade());
    System.out.println("Diem TB hoc phan PYTHON: "+ python.calSubjectMark()+"-"+java.calConversionMark()+"-"+python.calGrade());
    System.out.println("Điểm TB học kỳ: " + sv.calTermAverageMark());
    System.out.println("---------------------------");
}

}