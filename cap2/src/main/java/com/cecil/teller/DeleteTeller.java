package com.cecil.teller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cecil.connection.Connections;

public class DeleteTeller {
    public static void delete(String tname, String tpass) {
        try {
            String sql = "select * from teller where tname = ? and tpass = ?";
            PreparedStatement pstmt = Connections.openConn().prepareStatement(sql);

            pstmt.setString(1, tname);
            pstmt.setString(2, tpass);

            ResultSet r = pstmt.executeQuery();

            if (r.next()) {
                String sql1 = "delete from teller where tname = ? and tpass = ?";
                PreparedStatement pstmt1 = Connections.openConn().prepareStatement(sql1);

                pstmt1.setString(1, tname);
                pstmt1.setString(2, tpass);

                pstmt1.execute();

                System.out.println("=========================TELLER " + tname
                        + " DELETED Successfully!===============================");

            } else {
                System.out.println("===================TELLER " + tname + " was not found."
                        + " No Teller was DELETED !=========================");
            }
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } finally {
            Connections.closeConn();
        }
    }
}
