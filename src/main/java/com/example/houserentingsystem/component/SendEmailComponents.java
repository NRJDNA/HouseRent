package com.example.houserentingsystem.component;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Component;

/**
 * @author dangal_nirajan on 29/08/2022
 */
@Component
public class SendEmailComponents {
    public void sendEmail(String emailAddress, String Subject, String message) {
        try {
            Email email = new SimpleEmail();
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465);
            email.setAuthentication("copycon2022@gmail.com","copycon1234");
            email.setSSL(true);
            email.setFrom("copycon2022@gmail.com");
            email.setSubject("Registration");
            email.setMsg(message);
            email.addTo(emailAddress);
            email.send();
            System.out.println("Sent");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed");
        }
    }
}
