package com.example.houserentingsystem.service.admin;

import com.example.houserentingsystem.repo.admin.AdminRoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * @author dangal_nirajan on 30/08/2022
 */
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private AdminRoomRepo adminRoomRepo ;

    public void sendEmail(String toEmail,String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("copycon2022@gmail.com");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
}
