package com.cecil.account;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.cecil.Application;
import com.cecil.connection.Connections;
import com.cecil.logs.Logging;

public class ModifyAccount {
    static public void modifyDetails(int aid, String field, String new_val) {
        try {
            // Let Teller update a specific field of the account
            PreparedStatement pstmt = null;
            int rec = 0;
            switch (field) {
                case "aname":
                    if (new_val.equalsIgnoreCase("")) {
                        System.out.println("\u001B[31mAccount Holder's Name cannot be null!\u001B[0m");
                    } else {
                        pstmt = Connections.openConn()
                                .prepareStatement("update account set aname = ? where aid = ?");
                        pstmt.setString(1, new_val);
                        pstmt.setInt(2, aid);
                        rec = pstmt.executeUpdate();
                    }
                    break;

                case "email":
                    pstmt = Connections.openConn()
                            .prepareStatement("update account set email = ? where aid = ?");
                    pstmt.setString(1, new_val);
                    pstmt.setInt(2, aid);
                    rec = pstmt.executeUpdate();
                    break;

                case "phone":
                    try {
                        if (new_val.equalsIgnoreCase("")) {
                            pstmt = Connections.openConn()
                                    .prepareStatement("update account set phone = ? where aid = ?");
                            pstmt.setObject(1, null);
                            pstmt.setInt(2, aid);
                            rec = pstmt.executeUpdate();
                        } else {
                            Integer new_val_int = Integer.valueOf(new_val);
                            pstmt = Connections.openConn()
                                    .prepareStatement("update account set phone = ? where aid = ?");
                            pstmt.setInt(1, new_val_int);
                            pstmt.setInt(2, aid);
                            rec = pstmt.executeUpdate();
                        }
                    } catch (Exception e) {
                        System.out
                                .println("\u001B[31mAccount Holder's Phone Number can only contain numbers!\u001B[0m");
                    }
                    break;

                case "address":
                    pstmt = Connections.openConn()
                            .prepareStatement("update account set address = ? where aid = ?");
                    pstmt.setString(1, new_val);
                    pstmt.setInt(2, aid);
                    rec = pstmt.executeUpdate();
                    break;

                case "status":
                    if (CheckActive.isActive(aid)) {
                        System.out.print("Do you want to freeze Account " + aid + "? (Y/N)");
                        String input = Application.scan.nextLine();
                        if (input.equalsIgnoreCase("y")) {
                            pstmt = Connections.openConn()
                                    .prepareStatement("update account set status = 'frozen' where aid = ?");
                            pstmt.setInt(1, aid);
                            rec = pstmt.executeUpdate();
                        } else {
                            return;
                        }

                    } else {
                        System.out.print("Do you want to activate Account " + aid + "? (Y/N)");
                        String input = Application.scan.nextLine();
                        if (input.equalsIgnoreCase("y")) {
                            pstmt = Connections.openConn()
                                    .prepareStatement("update account set status = 'active' where aid = ?");
                            pstmt.setInt(1, aid);
                            rec = pstmt.executeUpdate();
                        } else {
                            return;
                        }

                    }

                    break;

                default:
                    System.out.println("\u001B[31mInvalid field.\u001B[0m");

            }

            if (rec > 0) {
                // Let Teller to see the details of the account to be modified
                ViewBalance.view(aid);
                Logging.openLog("Account with aid '" + aid + "' has been updated.");
                System.out.println("Record updated successfully.");
            } else {
                Logging.openLog("No account has been updated.");
                System.out.println("No records updated.");
            }

        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            Connections.closeConn();
        }
    };

}
