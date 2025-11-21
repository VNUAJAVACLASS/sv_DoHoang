package dao;

import model.User;
import dao.DBConnect;
import util.BCryptUtils;

import java.sql.*;

public class UserDAO {
	//kiem tra login
	public User loginByEmail(String email, String password) {
	    String sql = "SELECT * FROM tbluser WHERE email=?";

	    try (Connection conn = DBConnect.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setString(1, email);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            String hashed = rs.getString("password");

	            if (BCryptUtils.check(password, hashed)) {

	                User u = new User();
	                u.setUserName(rs.getString("user_name"));
	                u.setFullname(rs.getString("fullname"));
	                u.setEmail(rs.getString("email"));
	                u.setMobile(rs.getString("mobile"));
	                u.setAddress(rs.getString("address"));
	                u.setRole(rs.getInt("role"));

	                return u;
	            }
	        }

	    } catch (Exception e) { e.printStackTrace(); }

	    return null;
	}


    public boolean existedUser(String username) {
        String sql = "SELECT user_name FROM tbluser WHERE user_name=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            return ps.executeQuery().next();

        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public boolean register(User user) {
        String sql = "INSERT INTO tbluser(user_name, password, fullname, email, mobile, address, role) "
                   + "VALUES (?, ?, ?, ?, ?, ?, 0)";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            String hashed = BCryptUtils.hash(user.getPassword());

            ps.setString(1, user.getUserName());
            ps.setString(2, hashed);
            ps.setString(3, user.getFullname());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getMobile());
            ps.setString(6, user.getAddress());

            return ps.executeUpdate() > 0;

        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public User login(String username, String password) {
        String sql = "SELECT * FROM tbluser WHERE user_name=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String hashed = rs.getString("password");

                if (BCryptUtils.check(password, hashed)) {
                    User u = new User();
                    u.setUserName(rs.getString("user_name"));
                    u.setFullname(rs.getString("fullname"));
                    u.setEmail(rs.getString("email"));
                    u.setMobile(rs.getString("mobile"));
                    u.setAddress(rs.getString("address"));
                    u.setRole(rs.getInt("role"));
                    return u;
                }
            }

        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }
}
