package com.cecil.teller;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.cecil.connection.Connections;

public class CreateTeller {
    public static void createUser() {
        try {
            PreparedStatement pstmt = Connections.openConn().prepareStatement("insert * from user");
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } finally {
            Connections.closeConn();
        }
    }
}
