package com.cecil;
import java.util.Scanner;

public class Application {
    static boolean toContinue = true;
    static Scanner scan;
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

            switch (choice) {
            case 1:
                System.out.println("1");
                break;

            case 2:
                System.out.println("2");
                break;

            case 3:
                System.out.println("3");
                break;

            case 4:
                System.out.println("4");
                break;

            case 5:
                System.out.println("5");
                break;

            case 6:
                System.out.println("6");
                break;

            case 7:
                System.out.println("7");
                break;
                
            case 8:
                System.out.println("8");
                break;

            case 9:
                System.out.println("9");
                break;            
            default:
                System.out.println("Invalid Choice.");
                break;
            }
            scan.nextLine();
            System.out.println("Do you want to continue (y/n): ");
            String answer = scan.nextLine();
            if (answer.equalsIgnoreCase("y")){
                toContinue=true;
            } else {
                toContinue = false;
            }
        }
    }
}
