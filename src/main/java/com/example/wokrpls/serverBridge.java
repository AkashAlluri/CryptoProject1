package com.example.wokrpls;
import javafx.scene.control.Alert;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class serverBridge {
    static final String DB_URL = "jdbc:mysql://root@localhost:3306/crypto1";
    static final String USER = "root";
    static final String PASS = "toor";




    public boolean checkLogin(String username, String password){
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();) {
            System.out.println("Checking if password matches for username");
            String sql="SELECT password from customerdata WHERE user_name ='"+username+"'";
            ResultSet rs=stmt.executeQuery(sql);
            String passwordForGivenUserName= resultSetChecker(rs);
            if(passwordForGivenUserName.equals(password)){System.out.println("LOGIN SUCCESS");return true;}
            else {
                Alert a=new Alert(Alert.AlertType.NONE);
                a.setAlertType(Alert.AlertType.ERROR);
                a.setTitle("Login Failed");
                a.setContentText("Create a new account, or check username and password again");
                a.show();
                 return false;}

        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return true;
    }


    public String resultSetChecker(ResultSet resultSet) throws SQLException {
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (resultSet.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                String columnValue = resultSet.getString(i);
                return columnValue;
            }

        }
        return "";
    }


    public void createAccount(String username, String password, String sq, String sa){
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();) {
            System.out.println("Creating New Account");
            String sql = "INSERT into customerdata values ('"+username+"','"+password+"',0,0,0,0,'"+sq+"','"+sa+"')";
            stmt.execute(sql);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Alert a=new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.ERROR);
            a.setTitle("Username Exists");
            a.setContentText("Try Using a different Username");
            a.show();
            System.out.println("ERROR");
        }

    }
    public String getSecuirtyQuestion(String usn){
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();) {
            System.out.println("Getting Security Question");
            String sql = "SELECT sq from customerdata WHERE user_name ='"+usn+"'";
            ResultSet rs=stmt.executeQuery(sql);
            String securityQuestion=resultSetChecker(rs);
            return securityQuestion;


        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Alert a=new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.ERROR);
            a.setTitle("USERNAME DOES NOT EXIST");
            a.setContentText("This username does not exist, please create a new account");
            a.show();

        }
        return "";
    }

    public String getSecuirtyAnswer(String usn){
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();) {
            System.out.println("Getting Security Question");
            String sql = "SELECT sa from customerdata WHERE user_name ='"+usn+"'";
            ResultSet rs=stmt.executeQuery(sql);
            String securityAnswer=resultSetChecker(rs);
            return securityAnswer;


        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return "";
    }
    public void updatePassword(String userName, String password){
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();) {
            System.out.println("Transaction Register");
            String sql = "UPDATE customerdata set password='"+password+"' WHERE user_name ='" + userName + "'";
            stmt.executeUpdate(sql);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }













    public void registerTransactionBTC(String userName, Coin c){
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();) {
            System.out.println("Transaction Register");
            String sql = "UPDATE customerdata set btc_owned='"+c.getCoinsOwned()+"' WHERE user_name ='" + userName + "'";
            String sql2= "UPDATE customerdata set btc_spent='"+c.getAmountSpent()+"' WHERE user_name ='" + userName + "'";
            stmt.executeUpdate(sql);
            stmt.executeUpdate(sql2);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public double getTotalBTC(String userName) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();) {
            System.out.println("Reading TotalBTC");
            String sql = "SELECT btc_owned from customerdata WHERE user_name ='" + userName + "'";
            ResultSet rs = stmt.executeQuery(sql);
            String btcOwned = resultSetChecker(rs);
            return Double.parseDouble(btcOwned);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0.0;
    }
        public double getTotalSpentBTC (String userName){
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                 Statement stmt = conn.createStatement();) {
                System.out.println("Reading TotalBTC");
                String sql = "SELECT btc_spent from customerdata WHERE user_name ='" + userName + "'";
                ResultSet rs = stmt.executeQuery(sql);
                String btcSpent = resultSetChecker(rs);
                return Double.parseDouble(btcSpent);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return 0.0;

        }







    public void registerTransactionETH(String userName, Coin c){
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();) {
            System.out.println("Transaction Register");
            String sql = "UPDATE customerdata set eth_owned='"+c.getCoinsOwned()+"' WHERE user_name ='" + userName + "'";
            String sql2= "UPDATE customerdata set eth_spent='"+c.getAmountSpent()+"' WHERE user_name ='" + userName + "'";
            stmt.executeUpdate(sql);
            stmt.executeUpdate(sql2);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public double getTotalETH(String userName) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();) {
            System.out.println("Reading TotalETH");
            String sql = "SELECT eth_owned from customerdata WHERE user_name ='" + userName + "'";
            ResultSet rs = stmt.executeQuery(sql);
            String ethOwned = resultSetChecker(rs);
            return Double.parseDouble(ethOwned);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0.0;
    }
    public double getTotalSpentETH (String userName){
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();) {
            System.out.println("Reading TotalETH");
            String sql = "SELECT eth_spent from customerdata WHERE user_name ='" + userName + "'";
            ResultSet rs = stmt.executeQuery(sql);
            String ethSpent = resultSetChecker(rs);
            return Double.parseDouble(ethSpent);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0.0;

    }

}
