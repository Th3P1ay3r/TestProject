package com.testproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseRead {
    private final Connection con;

    public DatabaseRead(Connection con) {
        this.con = con;
    }

    public ArrayList<Offices> read() {
        ArrayList<Offices> aloffice = new ArrayList<>();
        try {
            if (con == null || con.isClosed()) {
                throw new SQLException("Connection is closed or null");
            }

            try (PreparedStatement ps = con.prepareStatement(
                    "SELECT officeCode, city, phone, addressLine1, addressLine2, state, country, postalCode, territory FROM offices WHERE country = ?")) {

                ps.setString(1, "USA");

                try (ResultSet rs = ps.executeQuery()) {
                    System.out.println(String.format("%-10s | %-20s | %-15s | %-30s | %-30s | %-15s | %-15s | %-10s | %-15s",
                            "OfficeCode", "City", "Phone", "Address1", "Address2", "State", "Country", "Postal", "Territory"));
                    System.out.println(new String(new char[180]).replace('\0', '-'));

                    while (rs.next()) {
                        aloffice.add(new Offices(
                                rs.getString(1),  // officeCode
                                rs.getString(2),  // city
                                rs.getString(3),  // phone
                                rs.getString(4),  // addressLine1
                                rs.getString(5),  // addressLine2
                                rs.getString(6),  // state
                                rs.getString(7),  // country
                                rs.getString(8),  // postalCode
                                rs.getString(9)   // territory
                        ));

                        System.out.println(String.format("%-10s | %-20s | %-15s | %-30s | %-30s | %-15s | %-15s | %-10s | %-15s",
                                rs.getString(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getString(4),
                                rs.getString(5),
                                rs.getString(6),
                                rs.getString(7),
                                rs.getString(8),
                                rs.getString(9)
                        ));
                    }
                    rs.close();
                    ps.close();
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            if (e.getErrorCode() == 0) {
                System.err.println("Connection lost - please reconnect");
            }
        }
        return aloffice;
    }
}
