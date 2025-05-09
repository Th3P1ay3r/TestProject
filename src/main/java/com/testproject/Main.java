package com.testproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    private Connection db_connect() {
        Connection con = null;
        try {
            Thread.sleep(30000);
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        int attempt = 1;

        while(true) {
            try {
                Thread.sleep(5000);
                con = DriverManager.getConnection("jdbc:mysql://db:3306/testdb?useSSL=false&allowPublicKeyRetrieval=true", "root", "root");
                System.out.println("Successful connected.");
                break;
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Fail to connect, attempt = " + attempt);
                attempt++;
            }
        }

        return con;
    }

    public static void main(String[] args) {
        Main m = new Main();
        int retries = 3;

        for (int i = 0; i < retries; i++) {
            try (Connection con = m.db_connect()) {
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