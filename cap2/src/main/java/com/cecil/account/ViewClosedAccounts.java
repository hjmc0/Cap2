package com.cecil.account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cecil.connection.Connections;
import com.cecil.logs.Logging;

public class ViewClosedAccounts {
    public static void viewCloseAcc() {
        try {
            PreparedStatement pstmt = Connections.openConn().prepareStatement("select * from ClosedAccount");
            ResultSet r = pstmt.executeQuery();

            int cnt = 1;
            String reset = "\u001B[0m"; // Reset color
            String red = "\u001B[31m"; // Red
            String green = "\u001B[32m"; // Green
            System.out.printf("%s%-6s | %-10s | %-15s | %-25s | %-15s | %-15s | %14s | %8s %s%n", red,
                    "Index.", "Account ID", "Name", "Email", "Phone Number", "Address", "Account Balance", "Status", reset);
                    System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
            while (r.next()) {
                Logging.openLog("All closed accounts were viewed by Teller.");
                System.out.printf("%s%-6s | %-10s | %-15s | %-25s | %-15s | %-15s | $%14.2f | %8s %s%n",
                        green, cnt + ".", r.getInt("aid"), r.getString("aname"), r.getString("email"),
                        r.getInt("phone"), r.getString("address"), r.getDouble("balance"), r.getString("status"), reset);
                cnt += 1;
            }

        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } finally {
            Connections.closeConn();
        }
    }
}
