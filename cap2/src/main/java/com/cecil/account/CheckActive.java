package com.cecil.account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cecil.connection.Connections;

public class CheckActive {
    public static boolean isActive(int acctID) {
        String sql = "select status from account where aid = ?";
        PreparedStatement pstmt;

        try {
            pstmt = Connections.openConn().prepareStatement(sql);
            pstmt.setInt(1, acctID);

            ResultSet r = pstmt.executeQuery();
            if (r.next()) {
                if (r.getString("status").equalsIgnoreCase("active")) {
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
}
