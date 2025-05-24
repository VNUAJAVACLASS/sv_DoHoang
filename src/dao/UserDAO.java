package dao;


import java.util.ArrayList;
import java.util.List;

import entity.Human;
import entity.Lecturer;
import entity.ScoreEntry;
import entity.Student;
import entity.Subject;

import java.sql.*;

public class UserDAO {
    private Connection connection;

    public UserDAO() {
        try {
            String dbURL = "jdbc:mysql://localhost:3306/lib/cosodulieu_mysql_structure.sql";
            String user = "root";
            String pass = "hoangdz12";
            connection = DriverManager.getConnection(dbURL, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Human> getAllHuman() {
        List<Human> listHuman = new ArrayList<>();
        String query = "SELECT * FROM tbl_users";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String userCode = rs.getString("user_code");
                String fullname = rs.getString("fullname");
                String address = rs.getString("address");
                String class_ = rs.getString("class");
                String password = rs.getString("password");
                int userType = rs.getInt("user_type");

                Human hm;
                if (userType == 1)
                    hm = new Lecturer(userCode, fullname, address, password);
                else
                    hm = new Student(userCode, fullname, address, class_);

                listHuman.add(hm);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listHuman;
    }
    
    public List<Human> getAllStudent() {
        List<Human> listHuman = new ArrayList<>();
        String query = "SELECT * FROM tbl_users WHERE user_type = 2";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String userCode = rs.getString("user_code");
                String fullname = rs.getString("fullname");
                String address = rs.getString("address");
                String class_ = rs.getString("class");
                String password = rs.getString("password");
                int userType = rs.getInt("user_type");

                Human hm;
                if (userType == 1)
                    hm = new Lecturer(userCode, fullname, address, password);
                else
                    hm = new Student(userCode, fullname, address, class_);

                listHuman.add(hm);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listHuman;
    }
    
    public List<Human> getAllLecturer() {
        List<Human> listHuman = new ArrayList<>();
        String query = "SELECT * FROM tbl_users WHERE user_type = 1";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String userCode = rs.getString("user_code");
                String fullname = rs.getString("fullname");
                String address = rs.getString("address");
                String class_ = rs.getString("class");
                String password = rs.getString("password");
                int userType = rs.getInt("user_type");

                Human hm;
                if (userType == 1)
                    hm = new Lecturer(userCode, fullname, address, password);
                else
                    hm = new Student(userCode, fullname, address, class_);

                listHuman.add(hm);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listHuman;
    }

    public Human getHumanByCode(String code) {
        Human hm = null;
        String query = "SELECT * FROM tbl_users WHERE user_code = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, code);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String userCode = rs.getString("user_code");
                String fullname = rs.getString("fullname");
                String address = rs.getString("address");
                String class_ = rs.getString("class");
                String password = rs.getString("password");
                int userType = rs.getInt("user_type");

                if (userType == 1)
                    hm = new Lecturer(userCode, fullname, address, password);
                else
                    hm = new Student(userCode, fullname, address, class_);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hm;
    }
    
    public Student getStudentByCode(String code) {
    	Student hm = null;
        String query = "SELECT * FROM tbl_users WHERE user_code = ? AND user_type = 2";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, code);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String userCode = rs.getString("user_code");
                String fullname = rs.getString("fullname");
                String address = rs.getString("address");
                String class_ = rs.getString("class");
                hm = new Student(userCode, fullname, address, class_);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hm;
    }

    public boolean addHuman(Human hm) {
        String query = "INSERT INTO tbl_users (user_code, fullname, address, class, password, user_type) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, hm.getCode());
            stmt.setString(2, hm.getFullname());
            stmt.setString(3, hm.getAddress());

            if (hm instanceof Student) {
                stmt.setString(4, ((Student) hm).getClass_());
                stmt.setString(5, null);
                stmt.setInt(6, 2);
            } else if (hm instanceof Lecturer) {
                stmt.setString(4, null);
                stmt.setString(5, ((Lecturer) hm).getPassword());
                stmt.setInt(6, 1);
            }

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateHuman(Human hm) {
        String query = "UPDATE tbl_users SET fullname = ?, address = ?, class = ?, password = ?, user_type = ? WHERE user_code = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, hm.getFullname());
            stmt.setString(2, hm.getAddress());

            if (hm instanceof Student) {
                stmt.setString(3, ((Student) hm).getClass_());
                stmt.setString(4, null);
                stmt.setInt(5, 2);
            } else if (hm instanceof Lecturer) {
                stmt.setString(3, null);
                stmt.setString(4, ((Lecturer) hm).getPassword());
                stmt.setInt(5, 1);
            }

            stmt.setString(6, hm.getCode());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteHumanByCode(String code) {
        String query = "DELETE FROM tbl_users WHERE user_code = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, code);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean addSubjectForStudent(Student student, Subject subject ) {
        String query = "INSERT INTO tbl_userssubject (user_code, subject_code) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, student.getCode());
            stmt.setString(2, subject.getSubjectCode());
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean addScore(Student student, Subject subject ) {
        String query = "UPDATE tbl_userssubject SET attendanceexammark = ? , midexammark1 = ? ,midexammark2 = ? ,midexammark3 = ? ,finalexammark WHERE user_code = ? AND subject_code = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
			int n = 1;
			for(ScoreEntry scoreEntry : subject.getScores()) {
				stmt.setFloat(n, (float) scoreEntry.getScore());
			}
            stmt.setString(6, student.getCode());
            stmt.setString(7, subject.getSubjectCode());
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}