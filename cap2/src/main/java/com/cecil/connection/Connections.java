package com.cecil.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connections {
    static Connection conn;

    static public Connection openConn() {
        try {
            // conn depends on whether you're using mysql or oracledb
<<<<<<< Updated upstream:cap2/src/main/java/com/cecil/connection/Connections.java
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cap2", "root", "dummy");
            // conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","cecil", "pass");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
                    "shurui99", "pass");
            // conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
            //         "shurui99", "pass");
=======
            // conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cap2", "root", "dummy");
            // conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","root", "dummy");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","shurui99", "pass");
>>>>>>> Stashed changes:cap2/src/main/java/connection/Connections.java
            assert conn != null : "Connection not successful";
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return conn;
    }

    static public void closeConn() {
        try {
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}
