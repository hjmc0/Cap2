package com.cecil.account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.InputMismatchException;

import com.cecil.Application;
import com.cecil.connection.Connections;

public class Deposit {

    public static void depositAmt(int aid) {

        try {
            LocalDateTime dateTime = LocalDateTime.now();
            Timestamp ts = Timestamp.valueOf(dateTime);

            String sql = "select aid, balance from account where aid  = ?";
            PreparedStatement pstmt = Connections.openConn().prepareStatement(sql);
            pstmt.setInt(1, aid);
            ResultSet r = pstmt.executeQuery();

            int temp = 0;
            double curBal = 0;
            double tempBal = 0;
            double newBal = 0;
            int sure = 0;
            String reset = "\u001B[0m"; // Reset color
            String cyan = "\u001B[36m"; // Cyan
            String red = "\u001B[31m"; // Red
            String green = "\u001B[32m"; // Green
            String bold = "\033[0;1m"; // Bold
            String yellow = "\u001B[33m"; // Yellow

            // retrieving
            while (r.next()) {
                temp = r.getInt("aid");
                curBal = r.getInt("balance");
            }

            if (temp == aid) {

                while (sure != 1) {
                    System.out.println("--------------------" + cyan + "CURRENT BALANCE $"
                            + String.format("%.2f", curBal) + reset + " --------------------");

                    Boolean valid = false;
                    while (!valid) {
                        System.out.print("Enter Deposit Amount: $"); // Entry of deposit amount and calculation
                        try {
                            tempBal = Math.abs(Application.scan.nextDouble());
                            Application.scan.nextLine();
                            valid = true;
                        } catch (InputMismatchException e) {
                            Application.scan.nextLine();
                            System.out.println(bold + red + "Invalid Deposit Amount. Please try again." + reset);
                        }

                    }
                    sure = 0;

                    while (sure != 1 && sure != 2) {
                        System.out.println("-----------------------------------------------------------");
                        System.out
                                .println("Continue to " + bold + green + "DEPOSIT $" + String.format("%.2f", tempBal)
                                        + "\u001B[0m" + " into ACCOUNT No.: " + aid + " ?");
                        System.out.println("1. To continue deposit");
                        System.out.println("2. To change amount");
                        System.out.print("Select : ");
                        sure = Application.scan.nextInt();
                        Application.scan.nextLine();
                        if ((sure != 2) && (sure != 1)) {
                            System.out.println(bold + red + "Invalid option" + reset);

                        }
                    }
                }
                newBal = tempBal + curBal;
                String sql2 = "select max(trans_id) from transaction";
                PreparedStatement pstmt1 = Connections.openConn().prepareStatement(sql2);
                ResultSet trans = pstmt1.executeQuery();
                trans.next();

                int nextTransId = trans.getInt(1) + 1;

                String deptSQL = "update Account set balance= ? where aid = ?";
                PreparedStatement pstmt2 = Connections.openConn().prepareStatement(deptSQL);
                pstmt2.setDouble(1, newBal);
                pstmt2.setInt(2, aid);
                pstmt2.execute();

                String deptSQLT = "insert into transaction(trans_id, trans_date, trans_type, aid, amount) values (?,?,?,?,?)";
                PreparedStatement pstmt3 = Connections.openConn().prepareStatement(deptSQLT);
                pstmt3.setInt(1, nextTransId);
                pstmt3.setTimestamp(2, ts);
                pstmt3.setString(3, "DEPOSIT");
                pstmt3.setInt(4, aid);
                pstmt3.setDouble(5, tempBal);
                pstmt3.execute();

                System.out.println("===============================================================");
                System.out.println("====================== " + bold + cyan + "DEPOSIT COMPLETED " + reset
                        + "======================");
                System.out.println("ACCOUNT NUMBER            :     " + aid);
                System.out.println("ORIGINAL TOTAL BALANCE    :   $ " + String.format("%.2f", curBal));
                System.out.println(
                        "DEPOSITED AMOUNT          :" + green + " + $ " + String.format("%.2f", tempBal) + reset);
                System.out.println("NEW TOTAL BALANCE         :   $ " + bold + String.format("%.2f", newBal) + reset);
                System.out.println("============================" + bold + yellow + "END" + reset
                        + "================================");

            } else {
                System.out.println(red + "------------ Account ID does not exist, try again! ------------" + reset);
                System.out.println("     ");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

}
