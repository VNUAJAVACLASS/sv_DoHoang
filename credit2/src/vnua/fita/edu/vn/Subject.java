package vnua.fita.edu.vn;

public abstract class Subject implements iCreditSubject{
	private String subjectCode;
	private String subjectName;
	private int credit;


	public Subject() {
		super();
	}

	public Subject(String subjectCode, String subjectName, int credit) {
		this.subjectCode = subjectCode;
		this.subjectName = subjectName;
		this.credit = credit;
	}
	

	// Diem thang 4
	public float calConversionMark() {
		float subjectMark = calSubjectMark();
		float conversionMark = -1;

		if (subjectMark <= 3.9) {
			conversionMark = 0;
		} else if (subjectMark <= 4.9) {
			conversionMark = 1;
		} else if (subjectMark <= 5.4) {
			conversionMark = (float) 1.5;
		} else if (subjectMark <= 6.4) {
			conversionMark = 2;
		} else if (subjectMark <= 6.9) {
			conversionMark = (float) 2.5;
		} else if (subjectMark <= 7.4) {
			conversionMark = 3;
		} else if (subjectMark <= 8.4) {
			conversionMark = (float) 3.5;
		} else if (subjectMark <= 10) {
			conversionMark = 4;
		}

		return conversionMark;
	}

	// 5 => C
	public String calGrade() {
		float subjectMark = calSubjectMark();
		String grade = null;

		if (subjectMark < 0) {
			grade = "ERORR";
		} else if (subjectMark <= 3.9) {
			grade = "F";
		} else if (subjectMark <= 4.9) {
			grade = "D";
		} else if (subjectMark <= 5.4) {
			grade = "D+";
		} else if (subjectMark <= 6.4) {
			grade = "C";
		} else if (subjectMark <= 6.9) {
			grade = "C+";
		} else if (subjectMark <= 7.4) {
			grade = "B";
		} else if (subjectMark <= 8.4) {
			grade = "B+";
		} else if (subjectMark <= 10) {
			grade = "A";
		}

		return grade;
	}

	public float calConversionMark(String grade) {
		float conversion = -1;
		switch (grade) {
		case "F":
			conversion = 0;
			break;
		case "D":
			conversion = 1;
			break;
		case "D+":
			conversion = (float) 1.5;
			break;
		case "C":
			conversion = 2;
			break;
		case "C+":
			conversion = (float) 2.5;
			break;
		case "B":
			conversion = 3;
			break;
		case "B+":
			conversion = (float) 3.5;
			break;
		case "A":
			conversion = 4;
			break;

		}
		
		return conversion;

	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	@Override
	public String toString() {
		return super.toString();
	}


	@Override
	public float calSubjectMark() {
		return 0;
	}
	
	

}