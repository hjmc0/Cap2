package com.cecil.teller;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.cecil.connection.Connections;
import com.cecil.logs.Logging;

public class CreateTeller {
    public static void create(String tname, String tpass) {
        try {
            String sql = "insert into teller(tname, tpass) values(?,?)";
            PreparedStatement pstmt = Connections.openConn().prepareStatement(sql);

            pstmt.setString(1, tname);
            pstmt.setString(2, tpass);
            if (tname.equalsIgnoreCase("admin")) {
                Logging.openLog("Teller has attempted to create Teller 'admin'");
                System.out.println("\u001B[31mCreation of admin is restricted.\u001B[0m");
            } else if (tname.equalsIgnoreCase("") || tpass.equalsIgnoreCase("")) {
                Logging.openLog("Teller has attempted to create Teller with null values");
                System.out.println("\u001B[31mLogin details of Teller is incomplete. Teller is not created.\u001B[0m");
            } else {
                pstmt.execute();
                Logging.openLog("Teller '" + tname + "' has been created.");
                System.out.println("=========================TELLER " + tname
                        + " CREATED Successfully!===============================");
            }
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } finally {
            Connections.closeConn();
        }
    }
}
