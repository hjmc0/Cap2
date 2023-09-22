package com.cecil.account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cecil.connection.Connections;

public class AddAccount {
        public static void add(String aname, double balance) {
                try {   
                        String sql = "select max(aid) from account";
                        PreparedStatement pstmt = Connections.openConn().prepareStatement(sql);
                
                        ResultSet s = pstmt.executeQuery();
                        s.next();
                        int aid = s.getInt(1) + 1;

                        String sql1 = "insert into account( aid, aname, balance) values(?,?,?)";
                        PreparedStatement pstmt1 = Connections.openConn().prepareStatement(sql1);
                        pstmt1.setInt(1, aid);
                        pstmt1.setString(2, aname);
                        pstmt1.setDouble(3, balance);
                        pstmt1.execute();

                        System.out.println("=========================ACCOUNT CREATED Successfully!===============================");
                        System.out.println("Name: " + aname);
                        System.out.println("Account No: " + aid);
                        System.out.println("CURRENT BALANCE IN ACCOUNT: $" + balance);
                        System.out.println("=====================================================================================");

                } catch (SQLException e) {
                        e.getMessage();
                } finally {
                        Connections.closeConn();
                }
        }

}