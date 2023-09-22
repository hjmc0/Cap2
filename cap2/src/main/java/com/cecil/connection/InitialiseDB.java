package com.cecil.connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InitialiseDB {
    static PreparedStatement pstmt;

    public static void dropDB() {
        String dropAcc = "BEGIN EXECUTE IMMEDIATE 'DROP TABLE account'; EXCEPTION WHEN OTHERS THEN NULL; END;";
        String dropTrans = "BEGIN EXECUTE IMMEDIATE 'DROP TABLE transaction'; EXCEPTION WHEN OTHERS THEN NULL; END;";

        try {
            pstmt = Connections.openConn().prepareStatement(dropAcc);
            pstmt.execute();
            System.out.println("Account dropped if exist");
            pstmt = Connections.openConn().prepareStatement(dropTrans);
            pstmt.execute();
            System.out.println("Transaction dropped if exist");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connections.closeConn();
    }
        
    }

    public static void createDB(){
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
    public static void initialise(){
        dropDB();
        createDB();
    }

    public static void main(String[] args) {
        initialise();
    }
}
