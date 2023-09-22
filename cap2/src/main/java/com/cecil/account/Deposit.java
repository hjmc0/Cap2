package com.cecil.account;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Scanner;

public class Deposit {

    public void depositAmt() {
                Scanner scan = new Scanner(System.in);
                
                try {
                    Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "john", "pass");
                    System.out.println("Connection Established");
                        assert conn != null : "No connection";
                        Statement stmt1 = conn.createStatement();
                    assert conn != null : "No connection";

                        // read balance from
                        // 
                    boolean aidMatcher = false;

                    while (!aidMatcher){
                        System.out.println("==================== DEOPSIT ====================");
                        System.out.println("Enter the Account ID:");
                        int acctID = scan.nextInt();
                        ResultSet r = stmt1.executeQuery("select aid, balance from Account where aid  = " + acctID);
                        int temp=0;
                        int curBal =0;
                        int tempBal=0;
                        Date date = Date.valueOf(LocalDate.now());

                        while (r.next()) {
                            temp = r.getInt("aid");
                            curBal = r.getInt("balance");
                        }
                        if (temp == acctID){
                            aidMatcher=true;
                            
                            System.out.println("Enter Deposit Amount: ");
                            tempBal = scan.nextInt();
                            curBal = tempBal + curBal;
                            ResultSet trans = stmt1.executeQuery("Select max(trans_id) from transaction");
                            trans.next();
                            int nextTransId =  trans.getInt(1)+1;

                            String deptSQL = "update Account set balance=" + curBal + " where aid = " + acctID ;
                            String deptSQLT = "insert into transaction(trans_id, trans_date, trans_type, aid, amount)" 
                                                                    + " values ("
                                                                    + nextTransId
                                                                    + ", sysdate,"
                                                                    + "'DEPOSIT',"
                                                                    + acctID + ","
                                                                    + tempBal + ")";
                                                                    System.out.println(deptSQL);
                                                                    System.out.println(deptSQLT);
                            stmt1.execute(deptSQL);
                            stmt1.execute(deptSQLT);
                            System.out.println(tempBal + "has been deposited into ACCOUNT NUMBER " + temp);
                            System.out.println("ACCOUNT " + temp +" has a NEW TOTAL BALANCE of $" + curBal);



                        }
                        else {
                            System.out.println("==============================================");
                            System.out.println("-----Account ID does not exist, try again!-----");
                            System.out.println("==============================================");
                            System.out.println("     ");
                        }    
                    }
                   


                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                scan.close();   
            }
            public static void main(String[] args) {
                Deposit dept = new Deposit();
                dept.depositAmt();
            }
            
}

