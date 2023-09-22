package com.cecil.account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cecil.connection.Connections;

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
                System.out.println("Account ID "+ aid +" does not exist!!!");
            }
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } finally {
            Connections.closeConn();
        }
    }


}
