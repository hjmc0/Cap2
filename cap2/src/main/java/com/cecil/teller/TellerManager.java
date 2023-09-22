package com.cecil.teller;

import com.cecil.Application;

public class TellerManager {
    public void execute(String operation) {

        switch (operation) {
            case "create":
                System.out.print("Enter New Teller Name: ");
                String create_tname = Application.scan.nextLine();
                System.out.print("Enter New Teller Password: ");
                String create_tpass = Application.scan.nextLine();
                CreateTeller.create(create_tname, create_tpass);
                break;

            case "login":
                System.out.print("Enter Teller Name: ");
                String login_tname = Application.scan.nextLine();
                System.out.print("Enter Teller Password: ");
                String login_tpass = Application.scan.nextLine();
                LoginTeller.login(login_tname, login_tpass);
                break;

            // case "delete":
            //     System.out.print("Enter Teller Name to close: ");
            //     String delete_tname = Application.scan.nextLine();
            //     System.out.print("Enter Teller Name to close: ");
            //     String delete_tpass = Application.scan.nextLine();
            //     Application.scan.nextLine();

            //     break;

            default:
                // code block
        }
    }
}
