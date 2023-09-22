package com.cecil.user;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.cecil.connection.Connections;

public class LoginUser {
    static boolean auth = false;
    public static boolean loginUser() {
        try {
            PreparedStatement pstmt = Connections.openConn().prepareStatement("select * from user");
            
            if (auth == true) {
                auth = true;
            }
        } catch (
        SQLException se) {
            System.out.println(se.getMessage());
        } finally {
            Connections.closeConn();
        }
        return auth;
    }
}
