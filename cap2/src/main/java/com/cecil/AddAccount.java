package com.cecil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AddAccount {
        Scanner scan = new Scanner(System.in);
        static boolean more = true;
        static int aid;
        static String aname;
        static int bal;
        
        public static void main(String[] args) {
                try {
                        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "john", "pass");
                        Statement stmt = conn.createStatement();


                        while (more) {
                                String addAcc = "insert into Account( aid, aname, bal)" 
                                                + "values ("
                                                + "'"+ aid+ "'"
                                                + "'"+ aname + "'"
                                                + "'"+ bal +"')"; 
                                
                        }

                } catch (SQLException e) {
                        
                        e.printStackTrace();
                }
                
        }
   
}