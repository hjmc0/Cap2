package com.cecil.teller;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.cecil.connection.Connections;

public class DeleteTeller {
        public static void delete(String tname, String tpass) {
        try {
            String sql = "delete from teller where tname = ? and tpass = ?";
            PreparedStatement pstmt = Connections.openConn().prepareStatement(sql);

            pstmt.setString(1, tname);
            pstmt.setString(2, tpass);

            pstmt.execute();

            System.out.println("=========================TELLER " + tname
                    + " DELETED Successfully!===============================");
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } finally {
            Connections.closeConn();
        }
    }
}
