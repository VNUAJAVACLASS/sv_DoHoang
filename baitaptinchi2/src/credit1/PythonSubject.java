package credit1;

public class PythonSubject extends Subject {
	private float attendanceMark;
	private float midExamMark;
    private float project;
	private float finalExamMark;

    public PythonSubject(String subjectCode, String subjectName, int credit, float attendance, float midExam, float project, float finalExam) {
        super(subjectCode, subjectName, credit);
        this.attendanceMark = attendance;
        this.midExamMark = midExam;
        this.project = project;
       this.finalExamMark = finalExam;
    }

    @Override
    public float calSubjectMark() {
        return attendanceMark * 0.1f + midExamMark * 0.2f + project * 0.2f + finalExamMark * 0.5f;
    }
}