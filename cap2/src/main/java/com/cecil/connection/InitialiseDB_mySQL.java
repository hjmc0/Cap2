package com.cecil.connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class InitialiseDB_mySQL {
    static PreparedStatement pstmt;

    public static void dropDB() {
        String dropAcc = "DROP TABLE IF EXISTS Account";
        String dropTrans = "DROP TABLE IF EXISTS Transaction";
        String dropTeller = "DROP TABLE IF EXISTS Teller";
        String dropClosedTrans = "DROP TABLE IF EXISTS ClosedTransaction";
        String dropClosedAcc = "DROP TABLE IF EXISTS ClosedAccount";

        try {
            pstmt = Connections.openConn().prepareStatement(dropTrans);
            pstmt.execute();
            System.out.println("Transaction table dropped if exists");
            pstmt = Connections.openConn().prepareStatement(dropAcc);
            pstmt.execute();
            System.out.println("Account table dropped if exists");
            pstmt = Connections.openConn().prepareStatement(dropTeller);
            pstmt.execute();
            System.out.println("Teller table dropped if exists");
            pstmt = Connections.openConn().prepareStatement(dropClosedTrans);
            pstmt.execute();
            System.out.println("Closed Transaction table dropped if exists");
            pstmt = Connections.openConn().prepareStatement(dropClosedAcc);
            pstmt.execute();
            System.out.println("Closed Account table dropped if exists");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connections.closeConn();
        }
    }

    public static void createDB() {
        String createAcc = "CREATE TABLE Account (aid INT PRIMARY KEY, aname VARCHAR(50) NOT NULL, email VARCHAR(30), phone INT, address VARCHAR(30), balance DECIMAL(38,2) NOT NULL, status VARCHAR(20) NOT NULL)";
        String createTrans = "CREATE TABLE Transaction (trans_id INT PRIMARY KEY, trans_date TIMESTAMP NOT NULL, trans_type VARCHAR(10) NOT NULL, aid INT, FOREIGN KEY (aid) REFERENCES Account(aid) ON DELETE CASCADE, amount DECIMAL(38,2) NOT NULL)";

        String createClosedAcc = "CREATE TABLE ClosedAccount (aid INT PRIMARY KEY, aname VARCHAR(50) NOT NULL, email VARCHAR(30), phone INT, address VARCHAR(30), balance DECIMAL(38,2) NOT NULL, status VARCHAR(20) NOT NULL)";
        String createClosedTrans = "CREATE TABLE ClosedTransaction (trans_id INT PRIMARY KEY, trans_date TIMESTAMP NOT NULL, trans_type VARCHAR(10) NOT NULL, aid INT, FOREIGN KEY (aid) REFERENCES ClosedAccount(aid) ON DELETE CASCADE, amount DECIMAL(38,2) NOT NULL)";

        String createTeller = "CREATE TABLE Teller (tname VARCHAR(6), tpass VARCHAR(6))";

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
            pstmt = Connections.openConn().prepareStatement(createClosedAcc);
            pstmt.execute();
            System.out.println("Closed Account table created");
            pstmt = Connections.openConn().prepareStatement(createClosedTrans);
            pstmt.execute();
            System.out.println("Closed Transaction table created");
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
            String insertTeller = "INSERT INTO Teller (tname, tpass) VALUES (?, ?)";
            pstmt = Connections.openConn().prepareStatement(insertTeller);
            pstmt.setString(1, "admin");
            pstmt.setString(2, "admin");
            pstmt.execute();

            String insertAcc1 = "INSERT INTO Account (aid, aname, email, phone, address, balance, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstmt = Connections.openConn().prepareStatement(insertAcc1);
            pstmt.setInt(1, 1);
            pstmt.setString(2, "hello1");
            pstmt.setString(3, "hw@test.com");
            pstmt.setInt(4, 12345678);
            pstmt.setString(5, "ntuclhub");
            pstmt.setDouble(6, 10000);
            pstmt.setString(7, "active");
            pstmt.execute();
            System.out.println("HERE 1");

            String insertAcc2 = "INSERT INTO Account (aid, aname, email, phone, address, balance, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstmt = Connections.openConn().prepareStatement(insertAcc2);
            pstmt.setInt(1, 2);
            pstmt.setString(2, "hello2");
            pstmt.setString(3, "ironman@test.com");
            pstmt.setInt(4, 23546235);
            pstmt.setString(5, "new york");
            pstmt.setDouble(6, 1003);
            pstmt.setString(7, "frozen");
            pstmt.execute();
            System.out.println("HERE 2");

            String insertAcc3 = "INSERT INTO Account (aid, aname, email, phone, address, balance, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstmt = Connections.openConn().prepareStatement(insertAcc3);
            pstmt.setInt(1, 3);
            pstmt.setString(2, "hello3");
            pstmt.setString(3, "capamerica@test.com");
            pstmt.setInt(4, 87654321);
            pstmt.setString(5, "ocean");
            pstmt.setDouble(6, 10043);
            pstmt.setString(7, "active");
            pstmt.execute();
            System.out.println("HERE 3");

            LocalDateTime ld = LocalDateTime.now();
            Timestamp ts = Timestamp.valueOf(ld);

            String insertTrans1 = "INSERT INTO Transaction (trans_id, trans_date, trans_type, aid, amount) VALUES (?, ?, ?, ?, ?)";
            pstmt = Connections.openConn().prepareStatement(insertTrans1);
            pstmt.setInt(1, 45);
            pstmt.setTimestamp(2, ts);
            pstmt.setString(3, "test1");
            pstmt.setInt(4, 1);
            pstmt.setDouble(5, 4352);
            pstmt.execute();
            System.out.println("THERE 1");

            String insertTrans2 = "INSERT INTO Transaction (trans_id, trans_date, trans_type, aid, amount) VALUES (?, ?, ?, ?, ?)";
            pstmt = Connections.openConn().prepareStatement(insertTrans2);
            pstmt.setInt(1, 46);
            pstmt.setTimestamp(2, ts);
            pstmt.setString(3, "test2");
            pstmt.setInt(4, 2);
            pstmt.setDouble(5, 46782);
            pstmt.execute();
            System.out.println("THERE 2");

            String insertTrans3 = "INSERT INTO Transaction (trans_id, trans_date, trans_type, aid, amount) VALUES (?, ?, ?, ?, ?)";
            pstmt = Connections.openConn().prepareStatement(insertTrans3);
            pstmt.setInt(1, 47);
            pstmt.setTimestamp(2, ts);
            pstmt.setString(3, "test3");
            pstmt.setInt(4, 3);
            pstmt.setDouble(5, 124543);
            pstmt.execute();
            System.out.println("THERE 3");

        } catch (SQLException e) {
            e.getMessage();
        } finally {
            Connections.closeConn();
        }
    }

    public static void main(String[] args) {
        initialise();
        createDummy();
    }
}