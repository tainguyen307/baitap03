package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ltweb";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "1234";
    private static Connection con;

    public static Connection getConnection() throws ClassNotFoundException {
    	con = null;
        try {
        	DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            con = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, e);
        }
        return con;
    }

    public static void main(String[] args) {
        try {
            Connection c = getConnection();
            if (c == null) {
                System.out.println("Something wrong");
            } else {
                System.out.println("OK");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}