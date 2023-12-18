package com.quyen.test.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.time.LocalDateTime;

@Service
public class EmailService {
    private JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Value("${spring.mail.username}")
    private String sender;

    public void sendAttachedMail(String receiver) throws MessagingException {
        // Creating a mime message
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        // Setting multipart as true for attachments to
        // be send
        mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setTo(receiver);
        mimeMessageHelper.setText("Email có đính kèm file");
        mimeMessageHelper.setSubject("[DEMO MAIL] Gửi mail kèm file");

        // Adding the attachment
        FileSystemResource file = new FileSystemResource(new File("data/1.jpg"));
        mimeMessageHelper.addAttachment(file.getFilename(), file);

        // Sending the mail
        javaMailSender.send(mimeMessage);

    }

    public void sendApprovedMail(String name, String receiver, LocalDateTime time) throws MessagingException {
        // Creating a mime message
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        // Setting multipart as true for attachments to be send
        mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setTo(receiver);
        mimeMessageHelper.setText("<b>Dear " + name +
                "</b>,<br>We are pleased to confirm your appointment will take place at " + time, true);
        mimeMessageHelper.setSubject("[Your Pets] Appointment Confirmation");

        // Adding the attachment
//        FileSystemResource file = new FileSystemResource(new File("data/1.jpg"));
//        mimeMessageHelper.addAttachment(file.getFilename(), file);

        // Sending the mail
        javaMailSender.send(mimeMessage);
    }

    public void sendRejectedMail(String name, String receiver) throws MessagingException {
        // Creating a mime message
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        // Setting multipart as true for attachments to be send
        mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setTo(receiver);
        mimeMessageHelper.setText("<b>Dear " + name +
                "</b>,<br>We are very sorry to not be able to arrange an appointment with you.<br>" +
                "We will contact you later!", true);
        mimeMessageHelper.setSubject("[Your Pets] Appointment Arrangement");

        // Adding the attachment
//        FileSystemResource file = new FileSystemResource(new File("data/1.jpg"));
//        mimeMessageHelper.addAttachment(file.getFilename(), file);

        // Sending the mail
        javaMailSender.send(mimeMessage);
    }


    public void sendOrderMail(String name, String receiver) throws MessagingException {
        // Creating a mime message
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        // Setting multipart as true for attachments to be send
        mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setTo(receiver);
        mimeMessageHelper.setText("<b>Dear " + name +
                "</b>,<br>We have received your information.\n<br>" +
                "We will send the product to you as soon as possible.", true);
        mimeMessageHelper.setSubject("[Your Pets] Order Confirmation");

        // Adding the attachment
//        FileSystemResource file = new FileSystemResource(new File("data/1.jpg"));
//        mimeMessageHelper.addAttachment(file.getFilename(), file);

        // Sending the mail
        javaMailSender.send(mimeMessage);
    }


}
