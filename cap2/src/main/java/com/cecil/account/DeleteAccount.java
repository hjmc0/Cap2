package com.cecil.account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.cecil.Application;
import com.cecil.connection.Connections;

public class DeleteAccount {

    static public void deleteAccount(int aid) {

        try {
            String sql = "select * from account where aid = ?";
            PreparedStatement pstmt = Connections.openConn().prepareStatement(sql);
            pstmt.setInt(1, aid);
            Boolean exist = true;

            ResultSet r = pstmt.executeQuery();
            String aname = "";
            System.out.println("===================================================");
            if (r.next()) {
                aname = r.getString("aname");
                System.out.println("Account ID      : " + r.getInt("aid"));
                System.out.println("Account Name    : " + r.getString("aname"));
                System.out.println("Account Email   : " + r.getString("email"));
                System.out.println("Account Phone No: " + r.getInt("phone"));
                System.out.println("Account Address : " + r.getString("address"));
                System.out.println("Account Balance : " + r.getDouble("balance"));
                System.out.println("===================================================");
            } else {
                System.out.println("Account ID " + aid + " does not exist!!!");
                exist = false;
            }
            Connections.closeConn();
            if (exist) {
                System.out.print("Above account will be deleted. Are you sure (y/n) ?????");
                String choice = Application.scan.nextLine();
                if (choice.equalsIgnoreCase("y")) {
                    // String deleteTrans = "delete from transaction where aid = "+aid;
                    // stmt.execute(deleteTrans);
                    String deleteAcc = "delete from account where aid = ?";
                    PreparedStatement pstmt1 = Connections.openConn().prepareStatement(deleteAcc);
                    pstmt1.setInt(1, aid);
                    pstmt1.execute();

                    System.out.println(aname + " (Account ID " + aid + ") and all transaction histories deleted !!");
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            Connections.closeConn();
        }
    }
}
