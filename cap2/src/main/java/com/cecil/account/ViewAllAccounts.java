package com.cecil.account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cecil.connection.Connections;
import com.cecil.logs.Logging;

public class ViewAllAccounts {
    public static void view() {
        try {
            PreparedStatement pstmt = Connections.openConn().prepareStatement("select * from Account");
            ResultSet r = pstmt.executeQuery();
            Logging.openLog("All accounts were viewed by Teller.");

            int cnt = 1;
            String reset = "\u001B[0m"; // Reset color
            String red = "\u001B[31m"; // Red
            String green = "\u001B[32m"; // Green
            String yellow = "\u001B[33m"; // Yellow
            String cyan = "\u001B[36m"; // Cyan
            String bold = "\033[0;1m"; // Bold
            
            System.out.println(bold+"----------------------------------------------------------------------------------------------------------------------------------"+reset);
            System.out.println(yellow+ "=========================================================="+bold+yellow+" ALL ACCOUNT"+reset+yellow+" ==========================================================="+ reset);
            System.out.println(bold+"----------------------------------------------------------------------------------------------------------------------------------"+reset);
            System.out.printf("%s%-6s | %-10s | %-15s | %-25s | %-15s | %-15s | %14s | %8s %s%n", bold+cyan,
                    "Index.", "Account ID", "Name", "Email", "Phone Number", "Address", "Account Balance", "Status",
                    reset);
            System.out.println(
                    "----------------------------------------------------------------------------------------------------------------------------------");
            while (r.next()) {
                if (r.getString("status").equalsIgnoreCase("active")) {
                    System.out.printf("%s%-6s | %-10s | %-15s | %-25s | %-15s | %-15s | $%14.2f | %8s %s%n",
                            green, cnt + ".", r.getInt("aid"), r.getString("aname"), r.getString("email"),
                            r.getInt("phone"), r.getString("address"), r.getDouble("balance"), r.getString("status"),
                            reset);
                } else if (r.getString("status").equalsIgnoreCase("frozen")) {
                    System.out.printf("%s%-6s | %-10s | %-15s | %-25s | %-15s | %-15s | $%14.2f | %8s %s%n",
                            red, cnt + ".", r.getInt("aid"), r.getString("aname"), r.getString("email"),
                            r.getInt("phone"), r.getString("address"), r.getDouble("balance"), r.getString("status"),
                            reset);
                }
                cnt += 1;
            }
            System.out.println("----------------------------------------------------------------"+bold+yellow+"END"+reset+"---------------------------------------------------------------");

        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } finally {
            Connections.closeConn();
        }
    }
}
