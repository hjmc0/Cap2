package com.cecil;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.cecil.account.ApplicationManager;
import com.cecil.logs.Logging;
import com.cecil.teller.LoginTeller;
import com.cecil.teller.TellerManager;

public class Application {
    static boolean toContinue = true;
    public static Scanner scan;

    public static void main(String[] args) {
        while (!LoginTeller.auth) {
            System.out.println("Dear Teller, please select from the following options");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Select: ");
            scan = new Scanner(System.in);
            String option = scan.nextLine();
            Logging.openLog("Teller chose option " + option + " at login page.");
            TellerManager tellmgr = new TellerManager();
            switch (option) {
                case "1":
                    tellmgr.execute("login");
                    break;
                case "2":
                    System.exit(-1);
                    break;

                default:
                    System.out.println("Invalid Choice.");
                    break;
            }
        }

        while (toContinue) {
            System.out.println("----------------------------------------");
            System.out.println("Please select from the following options");
            System.out.println("----------------------------------------");
            System.out.println("1. Add Account");
            System.out.println("2. View Accounts");
            System.out.println("3. Modify Account");
            System.out.println("4. Close Account");
            System.out.println("5. Deposit Money");
            System.out.println("6. Withdraw Money");
            System.out.println("7. View Balance");
            System.out.println("8. View Transaction History");
            System.out.println("9. Create Teller");
            System.out.println("10. Delete Teller");
            System.out.println("11. View Closed Accounts");
            System.out.println("12. View Closed Accounts Transaction History");
            System.out.println("13. Exit");

            int choice;

            try {
                System.out.print("Select Option: ");
                choice = scan.nextInt();
                scan.nextLine();
            } catch (InputMismatchException ie) {
                System.out.println("Invalid Choice! Please try again");
                scan.nextLine();
                continue;
            }

            Logging.openLog("Teller chose option " + choice + " at main page.");
            ApplicationManager appmgr = new ApplicationManager();
            TellerManager tellmgr = new TellerManager();

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
                    tellmgr.execute("create");
                    break;
                case 10:
                    tellmgr.execute("delete");
                    break;
                case 11:
                    appmgr.execute("viewcloseacc");
                    break;
                case 12:
                    appmgr.execute("viewcloseth");
                    break;
                case 13:
                    toContinue = false;
                    break;
                default:
                    System.out.println("Invalid Choice.");
                    break;
            }

            if (!toContinue) {
                break;
            }
            System.out.println("----------------------------------------------");
            System.out.print("Do you want to go back to main page? (y/n): ");
            String answer = scan.nextLine();
            Logging.openLog("Teller chose option " + answer + " when prompted to perform another transaction.");
            if (answer.equalsIgnoreCase("y")) {
                toContinue = true;
            } else if (answer.equalsIgnoreCase("n")){
                toContinue = false;
            } else {
                System.out.println("Invalid Choice. Taking 'n' as an input.");
                toContinue = false;
            }
        }
        scan.close();

    }
}
