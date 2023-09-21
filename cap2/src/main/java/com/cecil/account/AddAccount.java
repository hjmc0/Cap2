package com.cecil.account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AddAccount {
        
        public static void main(String[] args) {
                Scanner scan = new Scanner(System.in);
               
                try {
                        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "john", "pass");
                        Statement stmt = conn.createStatement();

                        System.out.println("Enter Account Number: ");
                        int aid = scan.nextLine();


                        while (more) {
                                String addAcc = "insert into Account( aid, aname, bal)" 
                                                + "values ("
                                                + "'"+ aid+ "'"
                                                + "'"+ aname + "'"
                                                + "'"+ bal +"')"; 

                                                int rec = stmt.executeUpdate(addAcc);
                                                System.out.println(rec + " records inserted");
                                                System.out.print("Enter another record? (y/n): ");
                                                String choice = scan.next();
                                                if (choice.equalsIgnoreCase("n")){
                                                    more = false;
                                                }
                        }

                } catch (SQLException e) {
                        
                        e.printStackTrace();
                }
                
        }
   
}