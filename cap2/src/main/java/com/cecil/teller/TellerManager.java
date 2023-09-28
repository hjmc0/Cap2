package com.cecil.teller;

import java.io.Console;

import com.cecil.Application;
import com.cecil.logs.Logging;

public class TellerManager {
    public void execute(String operation) {
        Console console = System.console();
        operation = operation.toLowerCase();
        switch (operation) {
            case "create":
                System.out.print("Enter New Teller Name: ");
                String create_tname = Application.scan.nextLine();
                char[] create_tpass = console.readPassword("Enter New Teller Password: ");
                CreateTeller.create(create_tname, new String(create_tpass));
                break;

            case "login":
                int tCount = 0;
                while (tCount < 3) {
                    System.out.print("Enter Teller Name: ");
                    String login_tname = Application.scan.nextLine();
                    char[] login_tpass = console.readPassword("Enter Teller Password: ");
                    LoginTeller.login(login_tname, new String(login_tpass));
                    if (LoginTeller.auth) {
                        break;
                    } else {
                        tCount += 1;
                        System.out.println("You have " + (3 - tCount) + " attempts left.");
                    }
                }
                if (LoginTeller.auth == false) {
                    System.out.println("3 failed login attempts. Exiting now!");
                    Logging.openLog("Someone has tried to login using three failed attempts.");
                    System.exit(-1);
                }
                break;

            case "delete":
                System.out.print("Enter Teller Name to close: ");
                String delete_tname = Application.scan.nextLine();
                char[] delete_tpass = console.readPassword("Enter Teller Password to close: ");
                DeleteTeller.delete(delete_tname, new String(delete_tpass));
                break;

            default:
                // code block
        }
    }
}
