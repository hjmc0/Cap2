package com.cecil.account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import connection.Connections;

public class AddAccount {
<<<<<<< Updated upstream
        public static void main(String[] args) {
                Scanner scan = new Scanner(System.in);
                boolean more = true;

                try {
                        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "john",
                                        "pass");
                        System.out.println("Connection Established");
                        assert conn != null : "No connection";
                        Statement stmt = conn.createStatement();

                        while (more) {
                                System.out.print("Enter New Account Number: ");
                                int aid = scan.nextInt();
                                scan.nextLine();
                                System.out.print("Enter Account Holder's Name: ");
                                String aname = scan.nextLine();
                                int bal = 0; // set default as $0

                                String addAcc = "insert into account( aid, aname, balance)"
                                                + "values ("
                                                + "" + aid + ","
                                                + "'" + aname + "',"
                                                + "" + bal + ")";

                                stmt.executeUpdate(addAcc);
                                System.out.println(
                                                "=========================ACCOUNT CREATED Successfully!===============================");
                                System.out.println("Name: " + aname);
                                System.out.println("Account No: " + aid);
                                System.out.println("CURRENT BALANCE IN ACCOUNT: $" + bal);
                                System.out.println(
                                                "=====================================================================================");
                                System.out.print("Do you want to create another account? (y/n): ");
                                String choice = scan.next();

                                if (choice.equalsIgnoreCase("n")) {
                                        more = false;
                                }
                        }

                } catch (SQLException e) {

                        e.printStackTrace();
                }
                scan.close();

=======
        public static void add(int aid, String aname, int balance) {
                try {
                        String sql = "insert into account( aid, aname, balance) values(?,?,?)";
                        PreparedStatement pstmt = Connections.openConn().prepareStatement(sql);

                        pstmt.setInt(1, aid);
                        pstmt.setString(2, aname);
                        pstmt.setInt(3, balance);
                        ResultSet r = pstmt.executeQuery();
                        System.out.println("=========================ACCOUNT CREATED Successfully!===============================");
                        System.out.println("Name: " + aname);
                        System.out.println("Account No: " + aid);
                        System.out.println("CURRENT BALANCE IN ACCOUNT: $" + balance);
                        System.out.println("=====================================================================================");

                } catch (SQLException e) {
                        e.getMessage();
                } finally {
                        Connections.closeConn();
                }
>>>>>>> Stashed changes
        }

}