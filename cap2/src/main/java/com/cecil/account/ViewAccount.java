package com.cecil.account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.Connections;

public class ViewAccount {
    private int aid;

    public ViewAccount(int aid) {
        this.aid = aid;
    }

    public int getAid() {
        return aid;
    }

    public void viewDetails() {
        try {
            PreparedStatement pstmt = Connections.openConn().prepareStatement("select * from Account where aid = ?");
            pstmt.setInt(1, getAid());
            ResultSet r = pstmt.executeQuery();

            System.out.println("================ ACCOUNT DETAILS ==============");
            while (r.next()) {
                System.out.println("Account ID      : " + r.getInt("aid"));
                System.out.println("Account Name    : " + r.getString("aname"));
                System.out.println("Account Balance : " + r.getInt("balance"));
            }
            System.out.println("===============================================");
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } finally {
            Connections.closeConn();
        }
    }

    public void viewPastTransactions() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "shurui99", "pass");
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

    // for testing
    public static void main(String[] args) {
        ViewAccount acc = new ViewAccount(1);
        acc.viewDetails();
        acc.viewPastTransactions();
    }

}
