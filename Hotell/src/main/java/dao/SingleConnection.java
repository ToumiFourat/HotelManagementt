package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingleConnection {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver"; // JDBC driver class
    private static final String URL = "jdbc:mysql://localhost:3306/hotel"; // Database URL
    private static final String USER = "root"; // Database username
    private static final String PASSWORD = "isimg"; // Database password
    private static Connection connection;

    // Private constructor to prevent instantiation
    private SingleConnection() {}

    // Initialize the connection
    private static void initConnection() {
        try {
            // Load the JDBC driver
            Class.forName(DRIVER);

            // Establish the connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connection established successfully.");
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Failed to establish database connection.");
            e.printStackTrace();
        }
    }

    // Provide the connection instance
    public static Connection getInstance() {
        if (connection == null) {
            initConnection(); // Call initConnection() to establish a connection if it's null
        }
        return connection;
    }
}
