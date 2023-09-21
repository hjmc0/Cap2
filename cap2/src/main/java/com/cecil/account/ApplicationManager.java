package com.cecil.account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ApplicationManager {
    public static void execute(String operation) {
        operation = operation.toLowerCase();
        Scanner scan = new Scanner(System.in);

        switch (operation) {
            case "add":
                boolean exit = false;
                while (!exit) {
                    System.out.print("Enter New Account Number: ");
                    int aid = scan.nextInt();
                    scan.nextLine();
                    System.out.print("Enter Account Holder's Name: ");
                    String aname = scan.nextLine();
                    int bal = 0; // set default as $0

                    AddAccount.add(aid, aname, bal);
                    System.out.print("Do you want to create another account? (y/n): ");
                    String choice = scan.next();

                    if (choice.equalsIgnoreCase("n")) {
                        exit = true;
                    }
                }
                scan.close();
                break;

            case "view":
                ViewAllAccounts.view();
                break;

            case "modify":
                System.out.println("Enter Account ID: ");
                int aid = scan.nextInt();
                System.out.println("Enter Field to edit: ");
                String field = scan.nextLine();
                System.out.println("Enter new value for "+field+ ": ");
                String new_val = scan.nextLine();
                
                break;

            case "close":

                break;

            case "deposit":

                break;
            case "withdraw":

                break;

            case "viewbal":
                System.out.print("Enter Account ID: ");
                int bal_aid = scan.nextInt();
                scan.nextLine();
                ViewBalance.view(bal_aid);
                break;

            default:
                // code block
        }

    }
    public static void main(String[] args) {
        ApplicationManager.execute("viewbal");
    }
}
