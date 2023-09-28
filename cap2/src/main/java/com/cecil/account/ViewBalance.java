package com.cecil.account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cecil.connection.Connections;
import com.cecil.logs.Logging;

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
                Logging.openLog("Account with aid '" + aid + "' has its balance viewed.");
                System.out.println("Account ID      : " + r.getInt("aid"));
                System.out.println("Name            : " + r.getString("aname"));
                System.out.println("Email           : " + r.getString("email"));
                System.out.println("Phone Number    : " + r.getInt("phone"));
                System.out.println("Address         : " + r.getString("address"));
                System.out.println("Account Balance : " + r.getDouble("balance"));
                System.out.println("Account Status  : " + r.getString("status"));
            }
            System.out.println("======================================================");

            if(!exist){
                Logging.openLog("No account has its balance viewed.");
                System.out.println("Account ID "+ aid +" does not exist!!!");
            }
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } finally {
            Connections.closeConn();
        }
    }


}
