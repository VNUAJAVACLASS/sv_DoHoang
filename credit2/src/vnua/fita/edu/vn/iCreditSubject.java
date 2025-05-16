package vnua.fita.edu.vn;


public interface iCreditSubject {
	float calConversionMark(String grade);

	String getSubjectCode();

	String getSubjectName();

	float calSubjectMark();
}