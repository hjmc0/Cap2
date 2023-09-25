package com.cecil.teller;

import java.io.Console;

import com.cecil.Application;

public class TellerManager {
    public void execute(String operation) {
        operation = operation.toLowerCase();
        switch (operation) {
            case "create":
                System.out.print("Enter New Teller Name: ");
                String create_tname = Application.scan.nextLine();
                System.out.print("Enter New Teller Password: ");
                String create_tpass = Application.scan.nextLine();
                CreateTeller.create(create_tname, create_tpass);
                break;

            case "login":
                int tCount = 0;
                while (tCount <3) {
                    System.out.print("Enter Teller Name: ");
                    String login_tname = Application.scan.nextLine();
                    Console console = System.console();
                    char[] login_tpass = console.readPassword("Enter Teller Password: ");
                    LoginTeller.login(login_tname, new String(login_tpass));
                    tCount +=1;
                }
                if (LoginTeller.auth == false) {
                    System.out.println("3 failed login attempts. Exiting now!");
                    System.exit(-1);
                }
                break;
                

            case "delete":
                System.out.print("Enter Teller Name to close: ");
                String delete_tname = Application.scan.nextLine();
                System.out.print("Enter Teller Password to close: ");
                String delete_tpass = Application.scan.nextLine();
                DeleteTeller.delete(delete_tname, delete_tpass);
                break;

            default:
                // code block
        }
    }
}
