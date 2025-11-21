package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/book_store",
                "root",
                ""
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
