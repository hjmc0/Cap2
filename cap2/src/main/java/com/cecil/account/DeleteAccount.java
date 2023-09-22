package com.cecil.account;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cecil.Application;

import com.cecil.connection.Connections;

public class DeleteAccount {

    static public void deleteAccount(int aid) {

        try {
            Statement stmt = Connections.openConn().createStatement();
            Boolean exist = true;
            ResultSet r_aname = stmt.executeQuery("select * from account where aid = " + aid);
            String aname = "";
            System.out.println("===================================================");
            if (r_aname.next()) {
                aname = r_aname.getString("aname");
                System.out.println("Account ID      : " + r_aname.getInt("aid"));
                System.out.println("Account Name    : " + aname);
                System.out.println("Account Balance : " + r_aname.getInt("balance"));
                System.out.println("===================================================");
            } else {
                System.out.println("Account ID " + aid + " does not exist!!!");
                exist = false;
            }

            if (exist) {
                System.out.print("Above account will be deleted. Are you sure (y/n) ?????");
                String choice = Application.scan.nextLine();
                if (choice.equalsIgnoreCase("y")) {
                    String deleteSql = "delete from account where aid = " + aid;
                    stmt.execute(deleteSql);
                    System.out.println(aname + " (Account ID " + aid + ") and all transaction histories deleted !!");
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            Connections.closeConn();
        }
    }
<<<<<<< Updated upstream

    public static void main(String[] args) {
        deleteAccount();
    }
=======
>>>>>>> Stashed changes
}
