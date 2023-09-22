package com.cecil.testing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.cecil.connection.Connections;


public class InsertTimeStamp {
 
    
    public static void main(String[] args) {
        Connection conn  = Connections.openConn();

        try {
            PreparedStatement stmt = conn.prepareStatement("insert into testdate values(?)");
            LocalDateTime ld = LocalDateTime.now();
            Timestamp ts = Timestamp.valueOf(ld);
            System.out.println(ts);
            stmt.setTimestamp(1, ts);
            stmt.executeUpdate();
            System.out.println("Rec inserted");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
