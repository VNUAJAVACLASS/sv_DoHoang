package vnua.fita.edu.vn;

public class JavaSubject extends Subject {
	private float attendanceMark;
	private float midExamMark;
	private float finalExamMark;

	public JavaSubject() {
		super();
	}

	public JavaSubject(float attendanceMark, float midExamMark, float finalExamMark) {
		super();
		this.attendanceMark = attendanceMark;
		this.midExamMark = midExamMark;
		this.finalExamMark = finalExamMark;
	}

	@Override
	public float calSubjectMark() {
		return attendanceMark * 0.1f + midExamMark * 0.4f + finalExamMark * 0.6f;
	}

	@Override
	public String toString() {
		float sumMark = calSubjectMark();
		return "Java [subjectCode=" + getSubjectCode() + ", attendanceMark=" + attendanceMark + ", midExamMark="
				+ midExamMark + ", finalExamMark=" + finalExamMark + " ,Sum= " + sumMark + "]";
	}

}