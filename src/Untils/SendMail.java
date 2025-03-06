/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Untils;

/**
 *
 * @author admin
 *
 */
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SendMail {

    public static void main(String[] args) throws MessagingException {
        final String from = "datdz2442005@gmail.com";
        final String password = "ukiu iokt ktun zzzv";

        //Properties : khai báo các thuộc tính
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2"); // Cài đặt TLS 1.2

        // create Authentivcator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);

            }
        };

        //Phiên làm việc
        Session session = Session.getInstance(props, auth);

        //Gửi email
        final String to = "datndph52674@gmail.com";

        //tạo 1 tin nhắn
        Message message = new MimeMessage(session);
        try {
            //Kiểu nội dung
            message.addHeader("Content-type", "text/HTML; charset=UTF-8");
            //Người gửi
            message.setFrom(new InternetAddress(from));
            //Người nhận
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            //Tiêu đề email
            message.setSubject("Test Email");
            //Quy định ngày gửi
            message.setSentDate(new Date());
            //Quy định email nhận phản hồi
            message.setReplyTo(InternetAddress.parse(from, true));
            //Nội dung thực tế
            //message.setText(body);
            
            //Send email (transport : vận chuyển) 
            Transport.send(message);
            
            System.out.println("Email sent successfully!");
        } catch (Exception e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }

    }
}
