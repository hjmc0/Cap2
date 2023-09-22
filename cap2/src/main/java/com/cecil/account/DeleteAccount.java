package com.cecil.account;

import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.Scanner;

import connection.Connections;

public class DeleteAccount {
    private int aid;

    public int getAid() {
        return aid;
    }
    
    static Scanner scan = new Scanner(System.in);
    
    static public void deleteAccount() {
        String choice;

        try {
            //Connections.openConn().setAutoCommit(false);
            System.out.println(Connections.openConn().getAutoCommit());
            Savepoint beforeDelete = Connections.openConn().setSavepoint("BeforeDelete");
            
            Statement stmt = Connections.openConn().createStatement();
            //String deleteSql =  "delete from account where aid = " + getAid();
            String deleteSql =  "delete from account where aid = 1";
            System.out.println(deleteSql);
            stmt.execute(deleteSql);
            Connections.openConn().commit();
            
            try {
                //System.out.println("Accounts and Transaction Histories related to Account ID" + getAid() + " will be deleted. Are you sure (y/n) ?");
                System.out.print("Accounts and Transaction Histories related to Account ID 1 will be deleted. Are you sure (y/n) ?");
                choice = scan.nextLine();
                if (choice.equalsIgnoreCase("y") ) {
                    //System.out.println("Account ID " + getAid() + " and all transaction histories deleted !!");
                    System.out.println("Account ID 1 and all transaction histories deleted !!");
                } else {
                Connections.openConn().rollback(beforeDelete);
                }
            } catch (NullPointerException ne) {
                System.out.println("NullPointerException Caught");
            }
            
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                Connections.openConn().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        deleteAccount();
    }
}
