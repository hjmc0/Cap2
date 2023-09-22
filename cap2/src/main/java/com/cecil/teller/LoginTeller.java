package com.cecil.user;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.cecil.connection.Connections;

public class LoginUser {
    static boolean auth = false;

    public static boolean loginUser() {
        try {
            String sql = "insert into user( aid, aname, balance) values(?,?,?)";
            PreparedStatement pstmt = Connections.openConn().prepareStatement(sql);

            if (auth == true) {
                auth = true;
            }
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } finally {
            Connections.closeConn();
        }
        return auth;
    }
}
