package com.jakubart;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelperSqlServer {

    private static String url = "jdbc:sqlserver://your_url";
    private static String user = "your_user";
    private static String passwd = "your_password";
    private static String dbDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
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
