package com.cecil.account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cecil.connection.Connections;
import com.cecil.logs.Logging;

public class AddAccount {
        public static void add(String aname, String email, Integer phone, String address, double balance) {
                try {
                        String sql = "WITH combinedAccount AS (SELECT aid FROM account UNION SELECT aid FROM closedaccount) SELECT MAX(aid) FROM combinedAccount";
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

                        if (aname.equalsIgnoreCase("")) {
                                Logging.openLog("No account has been created.");
                                System.out.println("\u001B[31mAccount Holder's Name cannot be null!\u001B[0m");
                                System.out.println(
                                                "===============================NO account created!==================================");
                        } else {
                                pstmt1.execute();

                                Logging.openLog("Account with aid '" + aid + "' has been created.");
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
                        }
                } catch (SQLException e) {
                        e.getMessage();
                } finally {
                        Connections.closeConn();
                }
        }

}