package com.cecil.account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cecil.Application;
import com.cecil.connection.Connections;
import com.cecil.logs.Logging;

public class ApplicationManager {
    public void execute(String operation) {
        operation = operation.toLowerCase();
        String input1, input2, input3, input4;
        Integer input3_int = null;
        switch (operation) {
            case "add":
                System.out.print("Enter Account Holder's Name: ");
                input1 = Application.scan.nextLine();
                if (input1.equals("q")) {
                    break;
                }
                System.out.print("Enter Account Holder's Email: ");
                input2 = Application.scan.nextLine();
                if (input2.equals("q")) {
                    break;
                }

                while (true) {
                    System.out.print("Enter Account Holder's Phone Number: ");
                    input3 = Application.scan.nextLine();
                    if (input3.equals("q")) {
                        break;
                    }
                    try {
                        input3_int = input3.equals("") ? null : Integer.valueOf(input3);
                        break;
                    } catch (NumberFormatException ne) {
                        System.out.println("Invalid Phone Number! Please try again");
                    }
                }

                System.out.print("Enter Account Holder's Address: ");
                input4 = Application.scan.nextLine();
                if (input4.equals("q")) {
                    break;
                }

                int bal = 0; // set default as $0
                AddAccount.add(input1, input2, input3_int, input4, bal);
                break;

            case "view":
                ViewAllAccounts.view();
                break;

            case "modify":
                int mod_aid;
                PreparedStatement pstmt;
                ResultSet r;
                System.out.print("Enter Account ID: ");
                input1 = Application.scan.nextLine();

                
                try {
                    mod_aid = Integer.valueOf(input1);
                } catch (Exception e) {
                    System.out.println("Invalid Account Number!");
                    break;
                }
                
                try {
                    pstmt = Connections.openConn().prepareStatement("select * from account where aid = ?");
                    pstmt.setInt(1, Integer.valueOf(input1));
                    r = pstmt.executeQuery();
                    if(!r.next()){
                        System.out.println("\u001B[31m-------------Account does not exist-----------------\u001B[0m");
                        break;
                    }
                }  catch (SQLException e) {
                    e.printStackTrace();
                }


                boolean to_continue = true;

                while (to_continue) {
                    if (!input1.equals("q")) {
                        System.out.println("1. Account Name");
                        System.out.println("2. Email");
                        System.out.println("3. Phone Number");
                        System.out.println("4. Address");
                        System.out.println("5. Freeze/Activate Account");
                        System.out.print("Enter Field to edit: ");

                        input2 = Application.scan.nextLine();
                        String input2_label = input2.equals("1") ? "Account Name"
                                : input2.equals("2") ? "Email"
                                        : input2.equals("3") ? "Phone Number"
                                                : input2.equals("4") ? "Address" : "Status";

                        if (!input2.equals("q")) {
                            if (input2.equals("5")) {
                                input3 = "";
                            } else {
                                System.out.print("Enter new value for " + input2_label + ": ");
                                input3 = Application.scan.nextLine();
                            }
                            if (!input3.equals("q")) {
                                input2 = input2.equals("1") ? "aname"
                                        : input2.equals("2") ? "email"
                                                : input2.equals("3") ? "phone"
                                                        : input2.equals("4") ? "address" : "status";

                                if (input2.equalsIgnoreCase("phone")) {
                                    try {
                                        Integer.valueOf(input3);
                                    } catch (Exception e) {
                                        System.out.println("Invalid Phone Number!");
                                        break;
                                    }
                                }
                                ModifyAccount.modifyDetails(mod_aid, input2, input3);
                            }
                        }
                    }
                    System.out.print("Do you want to edit another field? (Y/N)");
                    String response = Application.scan.nextLine();
                    Logging.openLog("Teller chose option " + response + " when prompted to edit another account field.");
                    if (response.equalsIgnoreCase("n")) {
                        to_continue = false;
                    }
                }
                break;

            case "close":
                System.out.print("Enter Account ID to close: ");
                input1 = Application.scan.nextLine();
                if (!input1.equals("q")) {
                    try {
                        int del_aid = Integer.valueOf(input1);
                        DeleteAccount.deleteAccount(del_aid);
                    } catch (NumberFormatException ne) {
                        System.out.println("Invalid Account Number!");
                    }
                }
                break;

            case "deposit":
                System.out.println("---------------------------------------------------------------");
                System.out.println("=========================== DEPOSIT ===========================");
                System.out.println("---------------------------------------------------------------");
                System.out.print("Enter the Account ID: ");
                input1 = Application.scan.nextLine();

                if (!input1.equals("q")) {
                    try {
                        int deposit_aid = Integer.valueOf(input1);
                        Deposit.depositAmt(deposit_aid);
                    } catch (NumberFormatException ne) {
                        System.out.println("Invalid Account Number!");
                    }
                }

                break;
            case "withdraw":
                System.out.println("---------------------------------------------------------------");
                System.out.println("=========================== WITHDRAW ===========================");
                System.out.println("---------------------------------------------------------------");
                System.out.print("Enter the Account ID: ");
                input1 = Application.scan.nextLine();
                if (!input1.equals("q")) {
                    try {
                        int withdraw_aid = Integer.valueOf(input1);
                        Withdraw.WithdrawAmt(withdraw_aid);
                    } catch (NumberFormatException ne) {
                        System.out.println("Invalid Account Number!");
                    }
                }
                ;
                break;

            case "viewbal":
                System.out.print("Enter Account ID: ");
                input1 = Application.scan.nextLine();
                if (!input1.equals("q")) {
                    try {
                        int view_aid = Integer.valueOf(input1);
                        ViewBalance.view(view_aid);
                    } catch (NumberFormatException ne) {
                        System.out.println("Invalid Account Number!");
                    }
                }

                break;
            case "viewtranshist":
                System.out.print("Enter Account ID: ");
                input1 = Application.scan.nextLine();
                if (!input1.equals("q")) {
                    try {
                        int view_trans_aid = Integer.valueOf(input1);
                        ViewTransHist.viewPastTransactions(view_trans_aid);
                    } catch (NumberFormatException ne) {
                        System.out.println("Invalid Account Number!");
                    }
                }
                break;
            case "viewcloseacc":
                ViewClosedAccounts.viewCloseAcc();
                break;
            case "viewcloseth":
                System.out.print("Enter Account ID: ");
                input1 = Application.scan.nextLine();
                if (!input1.equals("q")) {
                    try {
                        int view_closetrans_aid = Integer.valueOf(input1);
                        ViewClosedTransHist.viewCloseTransactions(view_closetrans_aid);
                    } catch (NumberFormatException ne) {
                        System.out.println("Invalid Account Number!");
                    }
                }
                break;
            default:
                // code block
        }

    }

}
