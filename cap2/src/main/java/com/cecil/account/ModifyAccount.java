package com.cecil.account;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.cecil.connection.Connections;

public class ModifyAccount {
    static public void modifyDetails(int aid, String field, String new_val) {
        try {
            // Let Teller update a specific field of the account
            if (field.equalsIgnoreCase("aname")) {
                PreparedStatement pstmt = Connections.openConn()
                        .prepareStatement("update account set aname = ? where aid = ?");
                pstmt.setString(1, new_val);
                pstmt.setInt(2, aid);
                int rec = pstmt.executeUpdate();
                if (rec > 0) {
                    // Let Teller to see the details of the account to be modified
                    ViewBalance.view(aid);
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

    // public static void main(String[] args) {
    // ModifyAccount acc = new ModifyAccount();
    // acc.modifyDetails(123, "aname", "John");
    // }
}
