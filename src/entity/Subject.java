package entity;

import java.util.ArrayList;
import java.util.List;

public class Subject{
	private String subjectName;
	private String subjectCode;
	private int credit;
	private List<ScoreEntry> scores;

	public Subject() {
		
	}
	
	public Subject(String subjectName, String subjectCode, int credit) {
		this.subjectName = subjectName;
		this.subjectCode = subjectCode;
		this.credit = credit;
		this.scores = new ArrayList<>();
	}

	public String getSubjectCode() {
		return subjectCode;
	}
	
	public String getSubjectName() {
		return subjectName;
	}

	public int getCredit() {
		return credit;
	}
	
    public void setScores(List<ScoreEntry> scores) {
        this.scores = scores;
    }

    public List<ScoreEntry> getScores() {
		return scores;
	}

	public void addScore(float score, float weight) {
        scores.add(new ScoreEntry(score, weight));
    }

	
	public float calConversionMark(String grade) {
        switch (grade) {
        case "A": return 4.0f;
        case "B+": return 3.5f;
        case "B": return 3.0f;
        case "C+": return 2.5f;
        case "C": return 2.0f;
        case "D+": return 1.5f;
        case "D": return 1.0f;
        default: return 0.0f;
        }
	}
	
	public String calGrade() {
        float subjectMark = calSubjectMark();
        if (subjectMark >= 8.5) return "A";
        if (subjectMark >= 7.5) return "B+";
        if (subjectMark >= 7.0) return "B";
        if (subjectMark >= 6.5) return "C+";
        if (subjectMark >= 6.0) return "C";
        if (subjectMark >= 5.5) return "D+";
        if (subjectMark >= 5.0) return "D";
        return "F";
	}

	private float calSubjectMark() {
		float total = 0;
		for (ScoreEntry scoreEntry : scores) {
			total += (float)scoreEntry.getScore() * scoreEntry.getWeight();
		}
		return total;
	}
	
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("Subject: ").append(subjectName)
	      .append(" (").append(subjectCode).append("), Credit: ").append(credit)
	      .append("\nScores:\n");

	    for (ScoreEntry entry : scores) {
	        sb.append(" - Score: ").append(entry.getScore());
	         sb.append(", Weight: ").append(entry.getWeight()).append("\n");
	    }
	    return sb.toString();
	}
}