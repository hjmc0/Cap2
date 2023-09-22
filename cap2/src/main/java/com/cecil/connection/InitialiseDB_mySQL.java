package com.cecil.connection;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class InitialiseDB_mySQL {
    static PreparedStatement pstmt;

    public static void dropDB() {
        String dropAcc = "DROP TABLE IF EXISTS Account";
        String dropTrans = "DROP TABLE IF EXISTS Transaction";
        String dropTeller = "DROP TABLE IF EXISTS teller";

        try {
            pstmt = Connections.openConn().prepareStatement(dropTrans);
            pstmt.execute();
            System.out.println("Transaction table dropped if exist");
            pstmt = Connections.openConn().prepareStatement(dropAcc);
            pstmt.execute();
            System.out.println("Account table dropped if exist");
            pstmt = Connections.openConn().prepareStatement(dropTeller);
            pstmt.execute();
            System.out.println("Teller table dropped if exist");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connections.closeConn();
        }

    }

    public static void createDB() {
        String createAcc = "CREATE TABLE Account (aid INT PRIMARY KEY, aname VARCHAR(50) NOT NULL, balance DECIMAL(38, 2) NOT NULL)";
        String createTrans = "CREATE TABLE Transaction (trans_id INT PRIMARY KEY, trans_date TIMESTAMP NOT NULL, trans_type VARCHAR(10) NOT NULL, aid INT, FOREIGN KEY (aid) REFERENCES Account(aid) ON DELETE CASCADE, amount DECIMAL(38, 2) NOT NULL)";
        String createTeller = "CREATE TABLE teller (tname VARCHAR(6), tpass VARCHAR(6))";
        try {
            pstmt = Connections.openConn().prepareStatement(createAcc);
            pstmt.execute();
            System.out.println("Account table created");
            pstmt = Connections.openConn().prepareStatement(createTrans);
            pstmt.execute();
            System.out.println("Transaction table created");
            pstmt = Connections.openConn().prepareStatement(createTeller);
            pstmt.execute();
            System.out.println("Teller table created");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            Connections.closeConn();
        }
    }

    public static void initialise() {
        dropDB();
        createDB();
    }

    public static void createDummy() {

        try {
            String insertTeller = "INSERT INTO teller(tname, tpass) VALUES (?, ?)";
            pstmt = Connections.openConn().prepareStatement(insertTeller);
            pstmt.setString(1, "admin");
            pstmt.setString(2, "admin");
            pstmt.execute();

            String insertAcc1 = "INSERT INTO Account(aid, aname, balance) VALUES (?, ?, ?)";
            pstmt = Connections.openConn().prepareStatement(insertAcc1);
            pstmt.setInt(1, 1);
            pstmt.setString(2, "hello1");
            pstmt.setBigDecimal(3, new BigDecimal("10000.00"));
            pstmt.execute();
            System.out.println("HERE 1");

            String insertAcc2 = "INSERT INTO Account(aid, aname, balance) VALUES (?, ?, ?)";
            pstmt = Connections.openConn().prepareStatement(insertAcc2);
            pstmt.setInt(1, 2);
            pstmt.setString(2, "hello2");
            pstmt.setBigDecimal(3, new BigDecimal("1003.00"));
            pstmt.execute();
            System.out.println("HERE 2");

            String insertAcc3 = "INSERT INTO Account(aid, aname, balance) VALUES (?, ?, ?)";
            pstmt = Connections.openConn().prepareStatement(insertAcc3);
            pstmt.setInt(1, 3);
            pstmt.setString(2, "hello3");
            pstmt.setBigDecimal(3, new BigDecimal("10043.00"));
            pstmt.execute();
            System.out.println("HERE 3");

            LocalDateTime ld = LocalDateTime.now();
            Timestamp ts = Timestamp.valueOf(ld);

            String insertTrans1 = "INSERT INTO Transaction(trans_id, trans_date, trans_type, aid, amount) VALUES (?, ?, ?, ?, ?)";
            pstmt = Connections.openConn().prepareStatement(insertTrans1);
            pstmt.setInt(1, 45);
            pstmt.setTimestamp(2, ts);
            pstmt.setString(3, "test1");
            pstmt.setInt(4, 1);
            pstmt.setBigDecimal(5, new BigDecimal("4352.00"));
            pstmt.execute();
            System.out.println("THERE 1");

            String insertTrans2 = "INSERT INTO Transaction(trans_id, trans_date, trans_type, aid, amount) VALUES (?, ?, ?, ?, ?)";
            pstmt = Connections.openConn().prepareStatement(insertTrans2);
            pstmt.setInt(1, 46);
            pstmt.setTimestamp(2, ts);
            pstmt.setString(3, "test2");
            pstmt.setInt(4, 2);
            pstmt.setBigDecimal(5, new BigDecimal("46782.00"));
            pstmt.execute();
            System.out.println("THERE 2");

            String insertTrans3 = "INSERT INTO Transaction(trans_id, trans_date, trans_type, aid, amount) VALUES (?, ?, ?, ?, ?)";
            pstmt = Connections.openConn().prepareStatement(insertTrans3);
            pstmt.setInt(1, 47);
            pstmt.setTimestamp(2, ts);
            pstmt.setString(3, "test3");
            pstmt.setInt(4, 3);
            pstmt.setBigDecimal(5, new BigDecimal("124543.00"));
            pstmt.execute();
            System.out.println("THERE 3");

        } catch (SQLException e) {
            e.getMessage();
        } finally {
            Connections.closeConn();
        }
    }

    public static void main(String[] args) {
        dropDB();
        createDB();
        initialise();
        createDummy();
    }
}
