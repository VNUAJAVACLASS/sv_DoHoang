package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import entity.ScoreEntry;
import entity.Subject;

public class SubjectDAO {
    private Connection connection;

    public SubjectDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/lib/cosodulieu_mysql_structure.sql";
            String user = "root";
            String pass = "hoangdz12";
            connection = DriverManager.getConnection(dbURL, user, pass);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // hoặc throw new RuntimeException(e);
        }
    }


    public List<Subject> getAllSubject() {
        List<Subject> listSubject = new ArrayList<>();
        String query = "SELECT * FROM tbl_subject";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String subjectCode = rs.getString("subject_code");
                String subjectName = rs.getString("subject_name");
                int credit = rs.getInt("credit");
                float attendanceexammark = rs.getFloat("attendanceexammark");
                float midexammark1 = rs.getFloat("midexammark1");
                float midexammark2 = rs.getFloat("midexammark2");
                float midexammark3 = rs.getFloat("midexammark3");
                float finalexammark = rs.getFloat("finalexammark");

                Subject subject = new Subject(subjectName, subjectCode, credit);
                subject.addScore(attendanceexammark, 0);
                subject.addScore(midexammark1, 0);
                subject.addScore(midexammark2, 0);
                subject.addScore(midexammark3, 0);
                subject.addScore(finalexammark, 0);

                listSubject.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listSubject;
    }

    public Subject getSubjectBySubjectCode(String subjectCode) {
        String query = "SELECT * FROM tbl_subject WHERE subject_code = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, subjectCode);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String subjectName = rs.getString("subject_name");
                int credit = rs.getInt("credit");

                return new Subject(subjectName, subjectCode, credit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addSubject(Subject subject) {
        String query = "INSERT INTO tbl_subject (subject_code, subject_name, credit, attendanceexammark , midexammark1, midexammark2, midexammark3, finalexammark) VALUES (?, ?, ?, ?, ? ,? ,? ,?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, subject.getSubjectCode());
            stmt.setString(2, subject.getSubjectName());
            stmt.setInt(3, subject.getCredit());
			int n = 3;
			for(ScoreEntry scoreEntry : subject.getScores()) {
				stmt.setFloat(n, (float) scoreEntry.getWeight());
			}
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateSubject(Subject subject) {
        String query = "UPDATE tbl_subject SET subject_name = ?, credit = ? WHERE subject_code = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, subject.getSubjectName());
            stmt.setInt(2, subject.getCredit());
            stmt.setString(3, subject.getSubjectCode());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteSubjectByCode(String subjectCode) {
        String query = "DELETE FROM tbl_subject WHERE subject_code = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, subjectCode);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}