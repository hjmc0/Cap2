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
            switch (field) {
                case "aname":
                    pstmt = Connections.openConn()
                            .prepareStatement("update account set aname = ? where aid = ?");
                    pstmt.setString(1, new_val);
                    pstmt.setInt(2, aid);
                    break;

                case "email":
                    pstmt = Connections.openConn()
                            .prepareStatement("update account set email = ? where aid = ?");
                    pstmt.setString(1, new_val);
                    pstmt.setInt(2, aid);
                    break;

                case "phone":
                    pstmt = Connections.openConn()
                            .prepareStatement("update account set phone = ? where aid = ?");
                    Integer new_val_int = Integer.valueOf(new_val);
                    pstmt.setInt(1, new_val_int);
                    pstmt.setInt(2, aid);
                    break;

                case "address":
                    pstmt = Connections.openConn()
                            .prepareStatement("update account set address = ? where aid = ?");
                    pstmt.setString(1, new_val);
                    pstmt.setInt(2, aid);
                    break;

                case "status":
                    if (CheckActive.isActive(aid)){
                        System.out.print("Do you want to freeze Account "+aid+"? (Y/N)");
                        String input = Application.scan.nextLine();
                        if(input.equalsIgnoreCase("y")){
                            pstmt = Connections.openConn().prepareStatement("update account set status = 'frozen' where aid = ?");
                            pstmt.setInt(1, aid);
                        }else{
                            return;
                        }

                    }
                    else{
                        System.out.print("Do you want to activate Account "+aid+"? (Y/N)");
                        String input = Application.scan.nextLine();
                        if(input.equalsIgnoreCase("y")){
                            pstmt = Connections.openConn().prepareStatement("update account set status = 'active' where aid = ?");
                            pstmt.setInt(1, aid);
                        }
                        else{
                            return;
                        }
                        
                    }
                    
                    break;

            }

            int rec = pstmt.executeUpdate();
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
