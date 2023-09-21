package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connections {
    static Connection conn;

    static public Connection openConn() {
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
                    "root", "dummy");
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