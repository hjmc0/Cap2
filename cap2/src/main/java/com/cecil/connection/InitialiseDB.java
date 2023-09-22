package com.cecil.connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class InitialiseDB {
    static PreparedStatement pstmt;

    public static void dropDB() {
        String dropAcc = "BEGIN EXECUTE IMMEDIATE 'DROP TABLE account'; EXCEPTION WHEN OTHERS THEN NULL; END;";
        String dropTrans = "BEGIN EXECUTE IMMEDIATE 'DROP TABLE transaction'; EXCEPTION WHEN OTHERS THEN NULL; END;";

        try {
            pstmt = Connections.openConn().prepareStatement(dropTrans);
            pstmt.execute();
            System.out.println("Transaction dropped if exist");
            pstmt = Connections.openConn().prepareStatement(dropAcc);
            pstmt.execute();
            System.out.println("Account dropped if exist");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connections.closeConn();
        }

    }

    public static void createDB() {
        String createAcc = "create table Account(aid number(3) primary key, aname varchar2(50) not null, balance number(38,2) not null)";
        String createTrans = "create table Transaction(trans_id number(10) primary key, trans_date timestamp not null, trans_type varchar2(10) not null, aid number(3), CONSTRAINT fk_aid FOREIGN KEY (aid) REFERENCES Account(aid) on delete cascade, amount number(38,2) not null)";
        try {
            pstmt = Connections.openConn().prepareStatement(createAcc);
            pstmt.execute();
            System.out.println("Account created");
            pstmt = Connections.openConn().prepareStatement(createTrans);
            pstmt.execute();
            System.out.println("Transaction created");

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

            String insertAcc1 = "insert into account(aid , aname , balance) values (? , ?, ?)";
            pstmt = Connections.openConn().prepareStatement(insertAcc1);
            pstmt.setInt(1, 1);
            pstmt.setString(2, "hello1");
            pstmt.setInt(3, 10000);
            pstmt.execute();
            System.out.println("HERE 1");

            String insertAcc2 = "insert into account(aid , aname , balance) values (? , ?, ?)";
            pstmt = Connections.openConn().prepareStatement(insertAcc2);
            pstmt.setInt(1, 2);
            pstmt.setString(2, "hello2");
            pstmt.setInt(3, 1003);
            pstmt.execute();
            System.out.println("HERE 2");

            String insertAcc3 = "insert into account(aid , aname , balance) values (? , ?, ?)";
            pstmt = Connections.openConn().prepareStatement(insertAcc3);
            pstmt.setInt(1, 3);
            pstmt.setString(2, "hello3");
            pstmt.setInt(3, 10043);
            pstmt.execute();
            System.out.println("HERE 3");

            LocalDateTime ld = LocalDateTime.now();
            Timestamp ts = Timestamp.valueOf(ld);

            String insertTrans1 = "insert into transaction(trans_id , trans_date , trans_type, aid, amount) values (? , ?, ? , ? , ?)";
            pstmt = Connections.openConn().prepareStatement(insertTrans1);
            pstmt.setInt(1, 45);
            pstmt.setTimestamp(2, ts);
            pstmt.setString(3, "test1");
            pstmt.setInt(4, 1);
            pstmt.setInt(5, 4352);
            pstmt.execute();
            System.out.println("THERE 1");

            String insertTrans2 = "insert into transaction(trans_id , trans_date , trans_type, aid, amount) values (? , ?, ? , ? , ?)";
            pstmt = Connections.openConn().prepareStatement(insertTrans2);
            pstmt.setInt(1, 46);
            pstmt.setTimestamp(2, ts);
            pstmt.setString(3, "test2");
            pstmt.setInt(4, 2);
            pstmt.setInt(5, 46782);
            pstmt.execute();
            System.out.println("THERE 2");

            String insertTrans3 = "insert into transaction(trans_id , trans_date , trans_type, aid, amount) values (? , ?, ? , ? , ?)";
            pstmt = Connections.openConn().prepareStatement(insertTrans3);
            pstmt.setInt(1, 47);
            pstmt.setTimestamp(2, ts);
            pstmt.setString(3, "test3");
            pstmt.setInt(4, 3);
            pstmt.setInt(5, 124543);
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
