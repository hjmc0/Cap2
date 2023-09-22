package com.cecil.account;

import com.cecil.Application;

public class ApplicationManager {
    public void execute(String operation) {
        operation = operation.toLowerCase();

        switch (operation) {
            case "add":
                System.out.print("Enter New Account Number: ");
                int aid = Application.scan.nextInt();
                Application.scan.nextLine();
                System.out.print("Enter Account Holder's Name: ");
                String aname = Application.scan.nextLine();
                int bal = 0; // set default as $0

                AddAccount.add(aid, aname, bal);

                break;

            case "view":
                ViewAllAccounts.view();
                break;

            case "modify":
                System.out.println("Enter Account ID: ");
                int mod_aid = Application.scan.nextInt();
                System.out.println("Enter Field to edit: ");
                Application.scan.nextLine();
                String field = Application.scan.nextLine();
                System.out.println("Enter new value for " + field + ": ");
                String new_val = Application.scan.nextLine();
                ModifyAccount.modifyDetails(mod_aid, field, new_val);

                break;

            case "close":

                break;

            case "deposit":

                break;
            case "withdraw":

                break;

            case "viewbal":
                System.out.print("Enter Account ID: ");
                int bal_aid = Application.scan.nextInt();
                Application.scan.nextLine();
                ViewBalance.view(bal_aid);
                break;

            default:
                // code block
        }

    }

}
