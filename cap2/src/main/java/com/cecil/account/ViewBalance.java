package com.cecil.account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.Connections;

public class ViewBalance {
    
    public static void view(int aid) {
        try {
            PreparedStatement pstmt = Connections.openConn().prepareStatement("select * from Account where aid = ?");
            pstmt.setInt(1, aid);
            ResultSet r = pstmt.executeQuery();

            boolean exist = false;
            System.out.println("================ ACCOUNT BALANCE DETAILS ==============");
            while (r.next()) {
                exist = true;
                System.out.println("Account ID      : " + r.getInt("aid"));
                System.out.println("Account Name    : " + r.getString("aname"));
                System.out.println("Account Balance : " + r.getInt("balance"));
            }
            System.out.println("======================================================");

            if(!exist){
                System.out.println("Account ID "+ aid +" does not exist");
            }
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } finally {
            Connections.closeConn();
        }
    }

    // move to transaction.java later
    public void viewPastTransactions() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "john", "pass");
            assert conn != null : "No Connection";
            PreparedStatement pstmt = conn.prepareStatement("select * from Transaction where aid = ?");
            pstmt.setInt(1, getAid());
            ResultSet r = pstmt.executeQuery();

            System.out.println("================ TRANSACTION DETAILS ==============");
            System.out.println();
            while (r.next()) {
                System.out.println("-----------------" + r.getDate("trans_date") + "----------------------");
                System.out.println("Transaction ID     : " + r.getInt("trans_id"));
                System.out.println("Transaction Type   : " + r.getString("trans_type"));
                System.out.println("Transaction Amount : " + r.getInt("Amount"));
                System.out.println();
            }
            System.out.println("===============================================");
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
    }

}
