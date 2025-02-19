package com.ms.email.Services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ms.email.Entities.Email;
import com.ms.email.Enums.StatusEmail;
import com.ms.email.Repositories.EmailRepository;



@Service
public class EmailService {
    
    @Autowired
    EmailRepository emailRepository;

    @Autowired
    JavaMailSender emailSender;


    @Value(value = "${spring.mail.username}")
    private String emailFrom;


    @Transactional
    public Email sendEmail(Email email) {
        try{
            email.setSendDateEmail(LocalDateTime.now());
            email.setEmailFrom(emailFrom);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email.getEmailTo());
            message.setSubject(email.getSubject());
            message.setText(email.getText());
            emailSender.send(message);

            email.setStatusEmail(StatusEmail.SENT);
        } catch (MailException e){
            email.setStatusEmail(StatusEmail.ERROR);
        } finally {
            return emailRepository.save(email);
        }
    }
}
