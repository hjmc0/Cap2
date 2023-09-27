package com.cecil.account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cecil.connection.Connections;

public class AddAccount {
        public static void add(String aname, String email, Integer phone, String address, double balance) {
                try {
                        String sql = "select max(aid) from (SELECT aid FROM account UNION SELECT aid FROM closedaccount) as combinedAccount";
                        PreparedStatement pstmt = Connections.openConn().prepareStatement(sql);

                        ResultSet s = pstmt.executeQuery();
                        s.next();
                        int aid = s.getInt(1) + 1;
                        String status = "active";

                        String sql1 = "insert into account( aid, aname, email, phone, address, balance, status) values(?,?,?,?,?,?,?)";
                        PreparedStatement pstmt1 = Connections.openConn().prepareStatement(sql1);
                        pstmt1.setInt(1, aid);
                        pstmt1.setString(2, aname);
                        pstmt1.setString(3, email);
                        pstmt1.setObject(4, phone);
                        pstmt1.setString(5, address);
                        pstmt1.setDouble(6, balance);
                        pstmt1.setString(7, status);
                        pstmt1.execute();

                        System.out.println(
                                        "=========================ACCOUNT CREATED Successfully!===============================");
                        System.out.println("Name: " + aname);
                        System.out.println("Account No: " + aid);
                        System.out.println("Account Status: " + status);
                        System.out.println("Email: " + email);
                        System.out.println("Phone No: " + phone);
                        System.out.println("Address: " + address);
                        System.out.println("CURRENT BALANCE IN ACCOUNT: $" + balance);
                        System.out.println(
                                        "=====================================================================================");

                } catch (SQLException e) {
                        e.getMessage();
                } finally {
                        Connections.closeConn();
                }
        }

}