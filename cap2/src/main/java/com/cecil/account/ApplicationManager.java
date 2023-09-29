package com.cecil.account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cecil.Application;
import com.cecil.connection.Connections;
import com.cecil.logs.Logging;

public class ApplicationManager {
    public void execute(String operation) {
        String reset = "\u001B[0m"; // Reset color
        String red = "\u001B[31m"; // Red
        // String green = "\u001B[32m"; // Green
        String yellow = "\u001B[33m"; // Yellow
        // String cyan = "\u001B[36m"; // Cyan
        String bold = "\033[0;1m"; // Bold

        operation = operation.toLowerCase();
        String input1, input2, input3, input4;
        Integer input3_int = null;
        switch (operation) {
            case "add":
                System.out.println(bold+ "---------------------------------------------------------------");
                System.out.println(yellow+ "========================="+bold+yellow+" ADD ACCOUNT"+reset+yellow+" ========================="+ reset);
                System.out.println(bold+ "---------------------------------------------------------------" +reset);
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
            System.out.println(bold+"---------------------------------------------------------------"+reset);
                System.out.println(yellow+ "======================="+bold+yellow+" Modify Account"+reset+yellow+" ======================="+ reset);
                System.out.println(bold+ "---------------------------------------------------------------"+ reset);
                int mod_aid;
                PreparedStatement pstmt;
                ResultSet r;
                System.out.print("Enter Account ID: ");
                input1 = Application.scan.nextLine();

                
                try {
                    mod_aid = Integer.valueOf(input1);
                } catch (Exception e) {
                    System.out.println(red+"Invalid Account Number!"+reset);
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
                                                : input2.equals("4") ? "Address"
                                                 : input2.equals("5")? "Status" : "";

                        if (!input2.equals("q")) {
                            if (input2.equals("5")) {
                                input3 = "";
                            }else if (input2_label.equals("")){
                                input3 = "";
                            } 
                            else {
                                System.out.print("Enter new value for " + input2_label + ": ");
                                input3 = Application.scan.nextLine();
                            }
                            if (!input3.equals("q")) {
                                input2 = input2.equals("1") ? "aname"
                                        : input2.equals("2") ? "email"
                                                : input2.equals("3") ? "phone"
                                                        : input2.equals("4") ? "address" 
                                                        : input2.equals("5")?"status": "";

                                ModifyAccount.modifyDetails(mod_aid, input2, input3);
                            }
                        }
                    }
                    System.out.print("Do you want to edit another field? (Y/N): ");
                    String response = Application.scan.nextLine();
                    Logging.openLog("Teller chose option " + response + " when prompted to edit another account field.");
                    if (response.equalsIgnoreCase("n")) {
                        to_continue = false;
                    }
                }
                break;

            case "close":
                System.out.println(bold+"---------------------------------------------------------------"+reset);
                System.out.println(yellow+ "========================"+bold+yellow+" CLOSE ACCOUNT"+reset+yellow+" ========================"+ reset);
                System.out.println(bold+"---------------------------------------------------------------"+reset);
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
                System.out.println(bold+"---------------------------------------------------------------"+reset);
                System.out.println(yellow+ "==========================="+bold+yellow+" DEPOSIT"+reset+yellow+" ==========================="+ reset);
                System.out.println(bold+"---------------------------------------------------------------"+reset);
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
                System.out.println(bold+"---------------------------------------------------------------"+reset);
                System.out.println(yellow+ "==========================="+bold+yellow+" WITHDRAW"+reset+yellow+" =========================="+ reset);
                System.out.println(bold+"---------------------------------------------------------------"+reset);
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
                System.out.println(bold+"---------------------------------------------------------------"+reset);
                System.out.println(yellow+ "========================="+bold+yellow+" View Balance"+reset+yellow+" ========================"+ reset);
                System.out.println(bold+"---------------------------------------------------------------"+reset);
                System.out.print("Enter Account ID: ");
                input1 = Application.scan.nextLine();
                if (!input1.equals("q")) {
                    try {
                        int view_aid = Integer.valueOf(input1);
                        ViewBalance.view(view_aid);
                    } catch (NumberFormatException ne) {
                        System.out.println(red+ "Invalid Account Number!"+ reset);
                    }
                }

                break;
            case "viewtranshist":
                System.out.println(bold+"---------------------------------------------------------------"+reset);
                System.out.println(yellow+ "====================="+bold+yellow+" Transaction History"+reset+yellow+" ====================="+ reset);
                System.out.println(bold+"---------------------------------------------------------------"+reset);
                System.out.print("Enter Account ID: ");
                input1 = Application.scan.nextLine();
                if (!input1.equals("q")) {
                    try {
                        int view_trans_aid = Integer.valueOf(input1);
                        ViewTransHist.viewPastTransactions(view_trans_aid);
                    } catch (NumberFormatException ne) {
                        System.out.println(red+"Invalid Account Number!"+reset);
                    }
                }
                break;
            case "viewcloseacc":
                                ViewClosedAccounts.viewCloseAcc();
                break;
            case "viewcloseth":
                System.out.println(bold+"---------------------------------------------------------------"+reset);
                System.out.println(yellow+ "============="+bold+yellow+" Closed Account Transaction History"+reset+yellow+" =============="+ reset);
                System.out.println(bold+"---------------------------------------------------------------"+reset);
                System.out.print("Enter Account ID: ");
                input1 = Application.scan.nextLine();
                if (!input1.equals("q")) {
                    try {
                        int view_closetrans_aid = Integer.valueOf(input1);
                        ViewClosedTransHist.viewCloseTransactions(view_closetrans_aid);
                    } catch (NumberFormatException ne) {
                        System.out.println(red+"Invalid Account Number!"+reset);
                    }
                }
                break;
            default:
                // code block
        }

    }

}
