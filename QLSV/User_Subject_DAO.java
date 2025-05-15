package QLSV;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class User_Subject_DAO {
	private Connection connection;
	
	public User_Subject_DAO() {
		try {
			String URL = "jdbc:ucanaccess://lib/QLNN.accdb";
			connection = DriverManager.getConnection(URL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean addUserSubject(User_Subject userSubject) {
		String query = "INSERT INTO User_Subject ( UserID, SubjectID,DiemHe1,DiemHe2,DiemHe3,DiemHe4,DiemHe5) VALUES(?,?,?,?,?,?,?)";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, userSubject.getuserID());
			stmt.setInt(2, userSubject.getsubjectID());
			stmt.setFloat(3, userSubject.getDiemHe1());
			stmt.setFloat(4, userSubject.getDiemHe2());
			stmt.setFloat(5, userSubject.getDiemHe3());
			stmt.setFloat(6, userSubject.getDiemHe4());
			stmt.setFloat(7, userSubject.getDiemHe5());

			int rowInserted = stmt.executeUpdate();
			return rowInserted > 0;
			} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateUserSubject(User_Subject userSubject) {
		String query = "UPDATE User_Subject SET UserID =?,SubjectID=?,DiemHe1=?,DiemHe2=?,DiemHe3=?,DiemHe4=?,DiemHe5=? WHERE UserSubject_ID=?";
		try (PreparedStatement stmt=connection.prepareStatement(query)){
			stmt.setInt(1, userSubject.getuserID());
			stmt.setInt(2, userSubject.getsubjectID());
			stmt.setFloat(3, userSubject.getDiemHe1());
			stmt.setFloat(4, userSubject.getDiemHe2());
			stmt.setFloat(5, userSubject.getDiemHe3());
			stmt.setFloat(6, userSubject.getDiemHe4());
			stmt.setFloat(7, userSubject.getDiemHe5());
			stmt.setInt(8, userSubject.getuserID());
			
			int rowsUpdate = stmt.executeUpdate();
			return rowsUpdate >0;			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteUserSubject(int userSubjectId) {
		String query = "DELETE FROM User_Subject WHERE UserSubject_ID =?";
		try (PreparedStatement stmt = connection.prepareStatement(query)){
			stmt.setInt(1, userSubjectId);
			int rowDeleted = stmt.executeUpdate();
			return rowDeleted >0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
