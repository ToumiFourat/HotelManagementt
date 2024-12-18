
package dao;

import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args) {
        Connection conn = SingleConnection.getInstance();
        if (conn != null) {
            System.out.println("Connection successful!");
        } else {
            System.out.println("Connection failed.");
        }
    }
}
