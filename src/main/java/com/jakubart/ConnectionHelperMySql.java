package com.jakubart;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelperMySql {

    private static String url = "jdbc:mysql://localhost:3306/jakosc?serverTimezone = ECT";
    private static String user = "root";
    private static String passwd = "admin";
    private static String dbDriver = "com.mysql.cj.jdbc.Driver";
    private static Connection conn;

    public static Connection getConnection() {
        try {
            Class.forName(dbDriver);
        } catch (ClassNotFoundException e) {
            System.out.println("Problem ze sterownikiem" + e.getMessage());
        }
        try {
            conn = DriverManager.getConnection(url, user, passwd);
        } catch (SQLException e) {
            System.out.println("Nie udało się nawiązać połącznia" + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }
}
