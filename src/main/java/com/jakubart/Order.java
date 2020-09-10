package com.jakubart;

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
}
