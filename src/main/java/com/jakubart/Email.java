package com.jakubart;

import java.util.ArrayList;

public class Email {

    private String textMessage;

    public Email(String textMessage) {
        this.textMessage = textMessage;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public static String writeTextMessage(ArrayList<Order> orders) {
        if (!orders.isEmpty()) {
            String textMessage;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Numer zamówienia: ").append(orders.get(0).getOrderNumber()).append("\n")
                    .append("Klient: ").append(orders.get(0).getClient()).append("\n")
                    .append("Realizacja dnia: ").append(orders.get(0).getRealization()).append("\n")
                    .append("Opis: ").append(orders.get(0).getDescription()).append("\n\n")
                    .append("Indeks: ").append(orders.get(0).getIndex()).append(", ").append("ilość: ").append(orders.get(0).getQuantity()).append("\n");

            for (int i = 1; i < orders.size(); i++) {
                if (orders.get(i - 1).getId() == orders.get(i).getId()) {
                    stringBuilder.append("Indeks: ").append(orders.get(i).getIndex()).append(", ").append("ilość: ").append(orders.get(i).getQuantity()).append("\n");
                } else {
                    stringBuilder.append("\n")
                            .append("**************************************************").append("\n")
                            .append("Numer zamówienia: ").append(orders.get(i).getOrderNumber()).append("\n")
                            .append("Klient: ").append(orders.get(i).getClient()).append("\n")
                            .append("Realizacja dnia: ").append(orders.get(i).getRealization()).append("\n")
                            .append("Opis: ").append(orders.get(i).getDescription()).append("\n\n")
                            .append("Indeks: ").append(orders.get(i).getIndex()).append(", ").append("ilość: ").append(orders.get(i).getQuantity()).append("\n");
                }
            }
            textMessage = stringBuilder.toString();
            return textMessage;
        }
        System.exit(-3);
        return "Brak zamówień na liście";
    }
}
