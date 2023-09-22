package com.cecil.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import com.cecil.connection.Connections;

public class ViewTransHist {
    // move to transaction.java later
    public static void viewPastTransactions(int aid) {
        Connection conn;
        try {
            conn = Connections.openConn();
            assert conn != null : "No Connection";
            PreparedStatement pstmt = conn.prepareStatement("select * from Transaction where aid = ?");
            pstmt.setInt(1, aid);
            ResultSet r = pstmt.executeQuery();

            SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
            
            System.out.println("================ TRANSACTION DETAILS ==============");
            System.out.println();

            while (r.next()) {
                // Date d = new Date(r.getTimestamp("trans_date"));
                System.out.println("-----------------" + dateFormat.format(r.getTimestamp("trans_date")) + "----------------------");
                System.out.println("Transaction ID     : " + r.getInt("trans_id"));
                System.out.println("Transaction Type   : " + r.getString("trans_type"));
                System.out.println("Transaction Amount : " + r.getInt("Amount"));
                System.out.println();
            }
            System.out.println("===============================================");
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }finally {
            Connections.closeConn();
    }
    }
}
