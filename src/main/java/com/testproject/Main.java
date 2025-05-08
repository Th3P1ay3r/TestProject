package com.testproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    private Connection get_Db_Connection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/testdb?autoReconnect=true&useSSL=false&serverTimezone=UTC",
                    "root",
                    ""
            );
            System.out.println("Database connected successfully");
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
        }
        return con;
    }

    public static void main(String[] args) {
        Main m = new Main();
        int retries = 3;

        for (int i = 0; i < retries; i++) {
            try (Connection con = m.get_Db_Connection()) {
                if (con != null) {
                    DatabaseRead dbReader = new DatabaseRead(con);
                    dbReader.read();
                    break; // Success - exit retry loop
                }
            } catch (SQLException e) {
                System.err.println("Attempt " + (i + 1) + " failed: " + e.getMessage());
                if (i == retries - 1) {
                    System.err.println("All retries exhausted");
                }
                try {
                    Thread.sleep(2000); // Wait before retry
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}