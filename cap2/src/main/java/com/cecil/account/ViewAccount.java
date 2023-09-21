package com.cecil.account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewAccount {
    private int aid;

    public ViewAccount(int aid) {
        this.aid = aid;
    }

    public int getAid() {
        return aid;
    }

    public void viewDetails() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "shurui99", "pass");
            assert conn != null : "No Connection";
            PreparedStatement pstmt = conn.prepareStatement("select * from Account where aid = ?");
            pstmt.setInt(1, getAid());
            ResultSet r = pstmt.executeQuery();
  
            System.out.println("================ ACCOUNT DETAILS ==============");
            while (r.next()) {
                System.out.println("Account ID      : "+ r.getInt("aid"));
                System.out.println("Account Name    : "+ r.getString("aname"));
                System.out.println("Account Balance : " + r.getInt("balance"));
            }
            System.out.println("===============================================");
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
    }

    public static void main(String[] args) {
        ViewAccount acc = new ViewAccount(2);
        acc.viewDetails();
    }

}
