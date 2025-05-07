package com.testproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseRead {  // Class names should be in PascalCase
    private final Connection con;  // Field to store the connection

    public DatabaseRead(Connection con) {  // Constructor
        this.con = con;  // Fixed assignment
    }

    public void read() {
        try {
            // Verify connection is still valid
            if (con == null || con.isClosed()) {
                throw new SQLException("Connection is closed or null");
            }

            try (PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM offices WHERE country = ?")) {

                ps.setString(1, "USA");

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        System.out.print(rs.getInt(1));
                        System.out.print("|");
                        System.out.print(rs.getString("city"));
                        System.out.println();
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            if (e.getErrorCode() == 0) { // Common code for connection issues
                System.err.println("Connection lost - please reconnect");
            }
        }
    }
}