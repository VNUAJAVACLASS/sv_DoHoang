package QLSV;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SubjectDAO {
	private Connection connection;

	public SubjectDAO() {
		try {
			String URL = "jdbc:ucanaccess://lib/QLSV.accdb";
			connection = DriverManager.getConnection(URL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean addSubject(Subject subject) {
		String query = "INSERT INTO Subject (SubjectName, Credit, Heso1, Heso2, Heso3, Heso4, Heso5) VALUES(?,?,?,?,?,?,?)";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, subject.getsubjectName());
			stmt.setInt(2, subject.getcredit());
			stmt.setInt(3, subject.getheSo1());
			stmt.setInt(4, subject.getheSo2());
			stmt.setInt(5, subject.getheSo3());
			stmt.setInt(6, subject.getheSo4());
			stmt.setInt(7, subject.getheSo5());

			int rowInserted = stmt.executeUpdate();
			return rowInserted > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateSubject(Subject subject) {
		String query = "UPDATE Subject SET SubjectName = ?, Credit = ?, Heso1 = ?, Heso2 = ?, Heso3 = ?, Heso4 = ?, Heso5 = ?  WHERE SubjectID = ?";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, subject.getsubjectName());
			stmt.setInt(2, subject.getcredit());
			stmt.setInt(3, subject.getheSo1());
			stmt.setInt(4, subject.getheSo2());
			stmt.setInt(5, subject.getheSo3());
			stmt.setInt(6, subject.getheSo4());
			stmt.setInt(7, subject.getheSo5());

			int rowsUpdate = stmt.executeUpdate();
			return rowsUpdate > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteSubject(int subjectId) {
		String query = "DELETE FROM Subject WHERE SubjectID = ?";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, subjectId);
			int rowDeleted = stmt.executeUpdate();
			return rowDeleted > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
