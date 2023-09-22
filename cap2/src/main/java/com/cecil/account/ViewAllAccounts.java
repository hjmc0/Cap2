package com.cecil.account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cecil.connection.Connections;

public class ViewAllAccounts {
    public static void view(){
        try {
            PreparedStatement pstmt = Connections.openConn().prepareStatement("select * from Account");
            ResultSet r = pstmt.executeQuery();

            while (r.next()) {
                System.out.println("=====================================================");
                System.out.println("Account ID      : " + r.getInt("aid"));
                System.out.println("Account Name    : " + r.getString("aname"));
                System.out.println("Account Balance : " + r.getInt("balance"));
                System.out.println("======================================================");
            }
            
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } finally {
            Connections.closeConn();
        }
    }
}
