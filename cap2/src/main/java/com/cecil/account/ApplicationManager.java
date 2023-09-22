package com.cecil.account;

import com.cecil.Application;

public class ApplicationManager {
    public void execute(String operation) {
        operation = operation.toLowerCase();
        String input1, input2, input3;
        switch (operation) {
            case "add":
                System.out.print("Enter Account Holder's Name: ");
                input2 = Application.scan.nextLine();
                int bal = 0; // set default as $0
                if (!input2.equals("q")) {
                    try {
                        AddAccount.add(input2, bal);
                    } catch (NumberFormatException ne) {
                        System.out.println("Invalid Account Number!");
                    }

                }

                break;

            case "view":
                ViewAllAccounts.view();
                break;

            case "modify":
                System.out.println("Enter Account ID: ");
                input1 = Application.scan.nextLine();
                if (!input1.equals("q")) {
                    System.out.println("Enter Field to edit: ");
                    input2 = Application.scan.nextLine();
                    if (!input2.equals("q")) {
                        System.out.println("Enter new value for " + input2 + ": ");
                        input3 = Application.scan.nextLine();
                        if (!input3.equals("q")) {
                            try {
                                int mod_aid = Integer.valueOf(input1);
                                ModifyAccount.modifyDetails(mod_aid, input2, input3);
                            } catch (Exception e) {
                                System.out.println("Invalid Account Number!");
                            }

                        }
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
                    }catch (NumberFormatException ne) {
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
                    }catch (NumberFormatException ne) {
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
            default:
                // code block
        }

    }

}
