package com.jakubart;

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

    
}
