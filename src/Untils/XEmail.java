/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Untils;

import java.util.Properties;
import javax.mail.Message;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 *
 * @author default
 */
public class XEmail {

    public void sendEmail(String to, String messageText) {
        try {
        String username = "datdz2442005@gmail.com";
        String password = "ukiu iokt ktun zzzv";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject("Thông Tin Mật Khẩu Không ");
            message.setContent("Mật Khẩu Đăng nhập Hệ Thống Là: " + messageText, "text/html");

            Transport.send(message);

            JOptionPane.showMessageDialog(null, "Gửi Thành Công Đến Email:" + to);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, String.valueOf(e));
            System.out.println(e);

        }
    }
}
