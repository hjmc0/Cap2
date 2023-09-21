package com.cecil.account;

import java.sql.PreparedStatement;
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
                pstmt.setString(1, new_val);
                pstmt.setInt(2, aid);
                System.out.println(pstmt);
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

    public static void main(String[] args) {
        ModifyAccount acc = new ModifyAccount();
        acc.modifyDetails(123, "aname", "John");
    }
}
