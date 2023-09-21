package com.cecil.account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import connection.Connections;

public class AddAccount {
        public static void add(int aid, String aname, int balance) {
                try {
                        String sql = "insert into account( aid, aname, balance) values(?,?,?)";
                        PreparedStatement pstmt = Connections.openConn().prepareStatement(sql);

                        pstmt.setInt(1, aid);
                        pstmt.setString(2, aname);
                        pstmt.setInt(3, balance);
                        ResultSet r = pstmt.executeQuery();
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