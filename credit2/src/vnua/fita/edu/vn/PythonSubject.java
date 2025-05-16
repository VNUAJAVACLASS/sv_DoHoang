package vnua.fita.edu.vn;


public class PythonSubject extends Subject {
	private float attendanceMark;
	private float midExamMark;
	private float finalExamMark;
	private float projectMark;
 
	public PythonSubject() {
		super();
	}

	public PythonSubject(float attendanceMark, float midExamMark, float finalExamMark, float projectMark) {
		super();
		this.attendanceMark = attendanceMark;
		this.midExamMark = midExamMark;
		this.finalExamMark = finalExamMark;
		this.projectMark = projectMark;
	}

	@Override
	public float calSubjectMark() {
		return attendanceMark * 0.1f + midExamMark * 0.2f + projectMark * 0.2f + finalExamMark * 0.6f;
	}

	@Override
	public String toString() {

		float sumMark = calSubjectMark();
		return "Python [subjectCode=" + getSubjectCode() + ", attendanceMark=" + attendanceMark + ", midExamMark="
				+ midExamMark + "  ,projectMark= " + projectMark + ", finalExamMark=" + finalExamMark
				+ " ,Sum= " + sumMark + "]";
	}

}