package com.cecil.teller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cecil.connection.Connections;
import com.cecil.logs.Logging;

public class LoginTeller {
    public static boolean auth = false;

    public static boolean login(String tname, String tpass) {
        try {
                String sql = "select * from teller where tname = ? and tpass = ?";
                PreparedStatement pstmt = Connections.openConn().prepareStatement(sql);

                pstmt.setString(1, tname);
                pstmt.setString(2, tpass);
                ResultSet r = pstmt.executeQuery();
                if(r.next() == true){
                    Logging.openLog("Teller '" + tname + "' has logged in.");
                    auth = true;
                } else {
                    System.out.println("Invalid login credentials");
                    System.out.println();
                }
            }catch (SQLException se) {
                System.out.println(se.getMessage());
            } finally {
                Connections.closeConn();
            }
            return auth;
    }
}
