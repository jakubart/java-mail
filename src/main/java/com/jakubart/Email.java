package com.jakubart;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

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

    public static void sendMessage(int idZam) {
        try (InputStream input = new FileInputStream("src/main/application.properties")) {
            Properties prop = new Properties();
            prop.put("mail.smtp.host", "smtp.wp.pl");
            prop.put("mail.smtp.auth", "true");

            prop.load(input);
            Session session = Session.getInstance(prop,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(prop.getProperty("email.login"), prop.getProperty("email.password"));
                        }
                    });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(prop.getProperty("email.adress")));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(prop.getProperty("email.to")));
                message.setSubject("Nowe zamóweinia w systemie XPRINTI");
                message.setText(Email.writeTextMessage(Order.readOrders(idZam)));
                Transport.send(message);
                System.out.println("Wiadomość wysłana");
            } catch (MessagingException e) {
                System.out.println("Error send message");
                e.printStackTrace();
                System.exit(-4);
            }
        } catch (FileNotFoundException e) {
            System.out.println("FIle not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IO Exception");
            e.printStackTrace();
        }
    }
}
