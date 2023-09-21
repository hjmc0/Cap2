package com.cecil.account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Deposit {
    public static void main(String[] args) {
                Scanner scan = new Scanner(System.in);
                
                try {
                    Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "john", "pass");
                    System.out.println("Connection Established");
                        assert conn != null : "No connection";
                        Statement stmt = conn.createStatement();

                        // read balance from
                        // 




                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
    }



}
