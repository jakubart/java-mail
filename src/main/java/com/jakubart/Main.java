package com.jakubart;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        counter.setMysqlCunter(counter.checkMysqlCounter());
        counter.setIdZamXprinti(counter.chcecIdZamSqlServer());
        if (counter.getIdZamXprinti() != counter.getMysqlCunter()) {
            Email.sendMessage(counter.getMysqlCunter());
            System.out.println(Email.writeTextMessage(Order.readOrders(counter.getMysqlCunter())));
            System.out.println("Zamówienia wysłane");
            Thread.sleep(5000);
        } else {
            System.out.println("Nie ma nowych zamówień");
            Thread.sleep(5000);
            System.exit(0);
        }
        counter.updateCounter(counter.getIdZamXprinti());
    }
}
