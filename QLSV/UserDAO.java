package QLSV;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
	private Connection connection;
	
	public UserDAO() {
		try {
			String dbURL = "jdbc:ucanacces://lib/QLSV.accdb";
			
			//Tao ket noi doi tuong
			connection = DriverManager.getConnection(dbURL);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<User> getAllUser(){
		List<User> userList = new ArrayList<>();
		String query =  "SELECT* FROM User";
	
		try (Statement stmt = connection.createStatement(); 
			ResultSet rs = stmt.executeQuery(query)) {
		
			while(rs.next()){
				
				int userID = rs.getInt("UserID");
				String fullName = rs.getString("Fullname");
				boolean gender = rs.getBoolean("Gender");
				String address = rs.getString("Address");
				String Password = rs.getString("Password");
				String userType = rs.getString("Type");
				
				User user = new User(userID,fullName,gender,address,Password,userType);
				userList.add(user);  
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return userList;
	}
	
	public User getUserById(int userId) {
		User user = null;
		String query = "SELECT * FROM User WHERE UserID = ?";
		
		try(PreparedStatement stmt = connection.prepareStatement(query)){
			
			stmt.setInt(1, userId);
				ResultSet rs = stmt.executeQuery();
				
			if(rs.next()) {
				String fullName = rs.getString("Fullname");
				boolean gender = rs.getBoolean("Gender");
				String address = rs.getString("Address");
				String Password = rs.getString("Password");
				String userType = rs.getString("Type");
				
				user = new User(userId,fullName,gender,address,Password,userType);}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return user;
	}
	
	public Boolean addUser(User user) {
		String query = "INSERT INTO User (UserID,Fullname,Gender,Address,Password,Type) VALUES (?,?,?,?,?,?)";
	
		try(PreparedStatement stmt = connection.prepareStatement(query)){
			stmt.setInt(1, user.getUserID());
			stmt.setString(3, user.getfullName());
			stmt.setBoolean(4, user.getgender());
			stmt.setString(6, user.getAddress());
			stmt.setString(8, user.getPassWord());
			stmt.setString(9, user.getUsertype());
			
			int rowsIntserted = stmt.executeUpdate();
			return rowsIntserted > 0;
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return false;
	}
	
	public Boolean updateUser(User user) {
		String query = "UPDATE User SET Fullname = ?, Gender = ?, Address = ?, Password = ?, Type = ? WHERE UserID = ?)";
	
		try(PreparedStatement stmt = connection.prepareStatement(query)){
			stmt.setInt(1, user.getUserID());
			stmt.setString(3, user.getfullName());
			stmt.setBoolean(4, user.getgender());
			stmt.setString(5, user.getAddress());
			stmt.setString(6, user.getPassWord());
			stmt.setString(7, user.getUsertype());
			
			int rowsIntserted = stmt.executeUpdate();
			return rowsIntserted > 0;
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return false;
	}
	
	public Boolean deleteUser(int UserID) {
		String query = "DELETE User UserID = ?)";
	
		try(PreparedStatement stmt = connection.prepareStatement(query)){
			stmt.setInt(1, UserID);
			
			int rowsDeleted = stmt.executeUpdate();
			return rowsDeleted > 0;
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return false;
	}

}

