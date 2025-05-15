package credit1;

public class JavaSubject extends Subject{
	private float attendanceMark;
	private float midExamMark;
	private float finalExamMark;
	
    public JavaSubject(String subjectCode, String subjectName, int credit, float attendance, float midExam, float finalExam) {
        super(subjectCode, subjectName, credit);
        this.attendanceMark = attendance;
        this.midExamMark = midExam;
       this.finalExamMark = finalExam;
    }

    @Override
    public float calSubjectMark() {
        return attendanceMark * 0.1f + midExamMark * 0.3f + finalExamMark * 0.6f;
    }

}