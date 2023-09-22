package com.cecil;

import java.util.Scanner;

import com.cecil.account.ApplicationManager;

public class Application {
    static boolean toContinue = true;
    public static Scanner scan;

    public static void main(String[] args) {
        while (toContinue) {
            System.out.println("Please select from the following options");
            System.out.println("------------------------------------------");
            System.out.println("1. Add Account");
            System.out.println("2. View Accounts");
            System.out.println("3. Modify Account");
            System.out.println("4. Close Account");
            System.out.println("5. Deposit Money");
            System.out.println("6. Withdraw Money");
            System.out.println("7. View Balance");
            System.out.println("8. View Transaction History");
            System.out.println("9. Exit");

            scan = new Scanner(System.in);
            int choice = scan.nextInt();
            scan.nextLine();
            //scan.close();
            ApplicationManager appmgr = new ApplicationManager();

            switch (choice) {
                case 1:
                    appmgr.execute("add");
                    break;

                case 2:
                    appmgr.execute("view");
                    break;

                case 3:
                    appmgr.execute("modify");
                    break;

                case 4:
                    appmgr.execute("close");
                    break;

                case 5:
                    appmgr.execute("deposit");
                    break;

                case 6:
                    appmgr.execute("withdraw");
                    break;

                case 7:
                    appmgr.execute("viewbal");
                    break;

                case 8:
                    appmgr.execute("viewtranshist");
                    break;

                case 9:
                    toContinue = false;
                    break;
                default:
                    System.out.println("Invalid Choice.");
                    break;
            }

            if (!toContinue) {
                break;
            }

            System.out.print("Do you want to perform other transactions? (y/n): ");
            String answer = scan.nextLine();
            if (answer.equalsIgnoreCase("y")) {
                toContinue = true;
            } else {
                toContinue = false;
            }
        }
        scan.close();
        
    }
}
