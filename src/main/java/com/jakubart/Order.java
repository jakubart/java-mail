package com.jakubart;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Order {

    private int id;
    private String created;
    private String realization;
    private String client;
    private String orderNumber;
    private String description;
    private int index;
    private double quantity;

    public Order(int id, String created, String realization, String client, String orderNumber, String description, int index, double quantity) {
        this.id = id;
        this.created = created;
        this.realization = realization;
        this.client = client;
        this.orderNumber = orderNumber;
        this.description = description;
        this.index = index;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getRealization() {
        return realization;
    }

    public void setRealization(String realization) {
        this.realization = realization;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public static ArrayList<Order> readOrders(int counterOrder) {
        Connection conn;
        Statement stmt;
        ResultSet rs;
        ArrayList<Order> listOfOrders = new ArrayList<>();
        conn = ConnectionHelperSqlServer.getConnection();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT\n" +
                    "      zc.[ID_ZAMOWIENIE_MASTER] as 'Id zamówienia'\n" +
                    "\t  ,cast(zm.DATA_ZAMOWIENIA as date) as 'Data zamówienia'\n" +
                    "\t  ,cast(zm.DATA_REALIZACJI as date) as 'Data realizacji'\n" +
                    "      ,sum([ILOSC_JP_ZAMOWIONA]) as 'ilość'\n" +
                    "\t  ,p.INDEKS_CDN as 'indeks'\n" +
                    "\t  ,o.NAZWA as 'Klient'\n" +
                    "\t  ,zm.NR_EXT as 'Numer zamowienia'\n" +
                    "\t  ,zm.OPIS as 'Opis' \n"+
                    "  FROM [XPRINTI_PRO].[dbo].[baza_zamowienia_child] zc\n" +
                    "inner join [XPRINTI_PRO].[dbo].[baza_zamowienia_master]  zm on zc.ID_ZAMOWIENIE_MASTER = zm.ID_ZAMOWIENIE_MASTER \n" +
                    "inner join  [XPRINTI_PRO].[dbo].[products]  p on CC_ID_PRODUKT = p.ID_PRODUCT\n" +
                    "inner join   [XPRINTI_PRO].[dbo].[baza_odbiorcy]  o on zm.ID_ODBIORCA = o.ID_ODBIORCA\n" +
                    "where o.nazwa not in ('ETYKIETY POLSKIE','MROŻENIE (DODRUK)', 'ROLMEX - GĘSI','ROLMEX DE- GĘSI','ROLMEX - BEEF HOUSE','ROLMEX-DROBGEN', 'PALETY', 'PODROBY') " +
                    "and zc.ID_ZAMOWIENIE_MASTER >'" + counterOrder + "'" +
                    "group by zc.ID_ZAMOWIENIE_MASTER,INDEKS_CDN, o.NAZWA,zm.DATA_ZAMOWIENIA,zm.DATA_REALIZACJI,zm.NR_EXT, zm.OPIS \n" +
                    "having sum([ILOSC_JP_ZAMOWIONA]) > 1");
            while (rs.next()) {
                listOfOrders.add(new Order(rs.getInt("Id zamówienia")
                        , rs.getString("Data zamówienia")
                        , rs.getString("Data realizacji")
                        , rs.getString("Klient")
                        , rs.getString("Numer zamowienia")
                        , rs.getString("Opis")
                        , rs.getInt("indeks")
                        , rs.getDouble("ilość")));
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error read orders from SqlServer");
            e.printStackTrace();
            System.exit(-2);
        }
        return listOfOrders;
    }
}
