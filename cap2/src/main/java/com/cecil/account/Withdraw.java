package com.cecil.account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Withdraw {
        public void WithdrawAmt() {
                Scanner scan = new Scanner(System.in);
                
                try {
                    Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "john", "pass");
                    System.out.println("Connection Established");
                    assert conn != null : "No connection";
                    Statement stmt1 = conn.createStatement();
                   
                    LocalDateTime dateTime = LocalDateTime.now();
            

                    Timestamp ts = Timestamp.valueOf(dateTime);
                    

                
                    boolean aidMatcher = false;

                    while (!aidMatcher){
                        System.out.println("---------------------------------------------------------------");
                        System.out.println("========================== WITHDRAW ==========================");
                        System.out.println("---------------------------------------------------------------");
                        System.out.print("Enter the Account ID: ");
                        int acctID = scan.nextInt();
                        ResultSet r = stmt1.executeQuery("select aid, balance from Account where aid  = " + acctID);

                        int temp=0;
                        double curBal =0;
                        double tempBal=0;
                        double newBal = 0;
                        int sure= 0;

                        // retrieving 
                        while (r.next()) {
                            temp = r.getInt("aid");
                            curBal = r.getInt("balance");
                        }

                        if (temp == acctID){
                            aidMatcher=true;
                            
                            while (sure!=1){
                                System.out.println("-------------------- CURRENT BALANCE: $" + curBal + " --------------------");
                                System.out.print("Enter Withdrawal Amount: $"); // Entry of deposit amount and calculation
                                tempBal = scan.nextDouble();
                                sure = 0;
                                
                                while(sure!=1 && sure!=2){
                                System.out.println("-----------------------------------------------------------");
                                System.out.println("Continue to WITHDRAW $"+ tempBal +" from ACCOUNT No.: " + acctID + " ?");
                                System.out.println("1. To continue withdrawal");
                                System.out.println("2. To change amount");
                                System.out.print("Select : ");
                                sure = scan.nextInt();
                                    if (sure!=2){
                                        System.out.println("Invalid option");
    
                                    }
                                }
                            }
                            newBal = curBal - tempBal;
                            ResultSet trans = stmt1.executeQuery("Select max(trans_id) from transaction");
                            trans.next();

                            

                            int nextTransId =  trans.getInt(1)+1;

                            // Update newest balance in ACCOUNT and Log transaction in TRANSACTION
                            String deptSQL = "update Account set balance=" + newBal + " where aid = " + acctID ;
                            String deptSQLT = "insert into transaction(trans_id, trans_date, trans_type, aid, amount)" 
                                                                    + " values ("
                                                                    + nextTransId
                                                                    + ", to_timestamp('" + ts + "', 'YYYY-MM-RR HH24:MI:SS.FF'),"
                                                                    + "'WITHDRAW',"
                                                                    + acctID + ","
                                                                    + tempBal + ")";

                            stmt1.execute(deptSQL);
                            stmt1.execute(deptSQLT);


                            System.out.println("===============================================================");
                            System.out.println("====================== WITHDRAW COMPLETED =====================");
                            System.out.println("ACCOUNT NUMBER            :   " + acctID);
                            System.out.println("ORIGINAL TOTAL BALANCE    : $ " + curBal);
                            System.out.println("WITHDRAW AMOUNT          : $ " + tempBal);
                            System.out.println("NEW TOTAL BALANCE         : $ " + newBal);
                            System.out.println("============================END================================");



                        }
                        else {
                            System.out.println("------------ Account ID does not exist, try again! ------------");
                            System.out.println("     ");
                        }    
                    }
                   


                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                scan.close();   
            }
}
