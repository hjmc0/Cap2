package com.cecil.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import com.cecil.connection.Connections;
import com.cecil.logs.Logging;

public class ViewTransHist {
    // move to transaction.java later
    public static void viewPastTransactions(int aid) {
        Connection conn;
        try {
            conn = Connections.openConn();
            PreparedStatement pstmt = conn.prepareStatement("select * from Transaction where aid = ?");
            pstmt.setInt(1, aid);
            ResultSet r = pstmt.executeQuery();

            SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

            System.out.println(
                    "================================== TRANSACTION DETAILS =================================");

            int cnt = 1;
            String reset = "\u001B[0m"; // Reset color
            String red = "\u001B[31m"; // Red
            String green = "\u001B[32m"; // Green
            String yellow = "\u001B[33m"; // Yellow
            System.out.printf("%s%-6s | %-20s | %-15s | %-17s | %-10s %s%n", yellow, "Index.", "Transaction Time",
                    "Transaction ID", "Transaction Type",
                    "Transaction Amount", reset);

            while (r.next()) {
                Logging.openLog("Account with aid '" + aid + "' has its transactions viewed.");
                if (r.getString("trans_type").equalsIgnoreCase("deposit")) {
                    System.out.printf("%-6s | %-20s | %-15s | %-22s | $%10.2f %s%n",
                            cnt + ".", (dateFormat.format(r.getTimestamp("trans_date")).toString()),
                            r.getString("trans_id"),
                            green + r.getString("trans_type"), r.getDouble("Amount"), reset);
                } else if (r.getString("trans_type").equalsIgnoreCase("withdraw")) {
                    System.out.printf("%-6s | %-20s | %-15s | %-22s | $%10.2f %s%n",
                            cnt + ".", (dateFormat.format(r.getTimestamp("trans_date")).toString()),
                            r.getString("trans_id"),
                            red + r.getString("trans_type"), r.getDouble("Amount"), reset);
                } else {
                    System.out.printf("%-6s | %-20s | %-15s | %-17s | $%10.2f %s%n",
                            cnt + ".", (dateFormat.format(r.getTimestamp("trans_date")).toString()),
                            r.getString("trans_id"),
                            r.getString("trans_type"), r.getDouble("Amount"), reset);
                }
                cnt += 1;
            }
            Logging.openLog("No account has its transactions viewed.");
            System.out.println(
                    "========================================================================================");
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } finally {
            Connections.closeConn();
        }
    }
}
