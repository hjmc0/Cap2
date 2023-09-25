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

            int cnt = 1;
            String reset = "\u001B[0m"; // Reset color
            String red = "\u001B[31m";   // Red
            String green = "\u001B[32m"; // Green
            // String white = "\u001B[37m";  // White
            System.out.printf("%s%-6s | %-15s | %-15s | %-10s %s%n", red, "Index.", "Account ID", "Holder Name", "Account Balance", reset);
            while (r.next()) {
                System.out.printf("%s%-6s | %-15s | %-15s | $%10.2f  %s%n", green, cnt + ".", r.getInt("aid"), r.getString("aname"), r.getDouble("balance"), reset);
                cnt += 1;
               
            }
            
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } finally {
            Connections.closeConn();
        }
    }
}
