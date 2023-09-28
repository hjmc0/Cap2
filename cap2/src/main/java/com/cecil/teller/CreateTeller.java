package com.cecil.teller;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.cecil.connection.Connections;

public class CreateTeller {
    public static void create(String tname, String tpass) {
        try {
            String sql = "insert into teller(tname, tpass) values(?,?)";
            PreparedStatement pstmt = Connections.openConn().prepareStatement(sql);

            pstmt.setString(1, tname);
            pstmt.setString(2, tpass);
            if (tname.equalsIgnoreCase("admin")) {
                System.out.println("\u001B[31mCreation of admin is restricted.\u001B[0m");
            } else {
                pstmt.execute();

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
