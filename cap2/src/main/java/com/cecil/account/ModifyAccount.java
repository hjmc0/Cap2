package com.cecil.account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.Connections;

public class ModifyAccount {
    public void modifyDetails(int aid, String field, String new_val) {
        try {
            // Let Teller to see the details of the account to be modified
            ViewAccount acc = new ViewAccount(1);
            acc.viewDetails();

            // Let Teller update a specific field of the account
            if (field.equalsIgnoreCase("aname")) {
                PreparedStatement pstmt = Connections.openConn()
                        .prepareStatement("update account set aname = ? where aid = ?");
                pstmt.setString(1, "Danny");
                pstmt.setInt(2, 1);
                int rec = pstmt.executeUpdate();
                if (rec > 0) {
                    System.out.println("Record updated successfully.");
                } else {
                    System.out.println("No records updated.");
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            Connections.closeConn();
        }
    };
}
