// file no longer used

//package com.example.wokrpls;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//public class serverBridge {
//    static final String DB_URL = "jdbc:mysql://localhost/test1";
//    static final String USER = "root";
//    static final String PASS = "toor";
//
//    public static void main(String[] args) {
//        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
//             Statement stmt = conn.createStatement();) {
//            System.out.println("Inserting...");
//            String sql="INSERT into customerdata VALUES ('akash_a', 'password', 10.23,2345.34,0,0)";
//            stmt.executeUpdate(sql);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
//}
