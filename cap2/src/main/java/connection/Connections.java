package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connections {
    static Connection conn;

    static public void openConn(String sql) {
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
                    "root", "dummy");
            assert conn != null : "Connection not successful";
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    static public void closeConn() {
        try {
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}
