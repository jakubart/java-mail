package com.jakubart;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Counter {

    private int mysqlCunter;
    private int idZamXprinti;

    public Counter(){}

    public Counter(int mysqlCunter, int idZamXprinti) {
        this.mysqlCunter = mysqlCunter;
        this.idZamXprinti = idZamXprinti;
    }

    public int getMysqlCunter() {
        return mysqlCunter;
    }

    public void setMysqlCunter(int mysqlCunter) {
        this.mysqlCunter = mysqlCunter;
    }

    public int getIdZamXprinti() {
        return idZamXprinti;
    }

    public void setIdZamXprinti(int idZamXprinti) {
        this.idZamXprinti = idZamXprinti;
    }

    public int checkMysqlCounter() {
        int counter = 0;
        Connection conn;
        Statement stmt;
        ResultSet rs;
        conn = ConnectionHelperMySql.getConnection();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT id_zam FROM zamowienia_email");
            if (rs.next()) {
                counter = rs.getInt("id_zam");
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error check counter from Mysql");
            e.printStackTrace();
            System.exit(-2);
        }
        return counter;
    }

}
