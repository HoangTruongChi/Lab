package com.hust.labregister.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailUtils extends MyRunnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailUtils.class);
    private static final Long TIMEOUT = 60000L;

    private static final String USERNAME = "tuhalang007@gmail.com";
    private static final String PASSWORD = "Phamhung007@";

    private String emailTo;
    private String content;
    private String subject;

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public static void send(String emailTo, String content, String subject) {
        MailUtils sendEmail = new MailUtils();
        sendEmail.setEmailTo(emailTo);
        sendEmail.setSubject(subject);
        sendEmail.setContent(content);
        Thread thread = new Thread(sendEmail);
        thread.start();

        Long startTimeMillis = System.currentTimeMillis();
        Long endTimeMillis = startTimeMillis + TIMEOUT;

        while (thread.isAlive()) {
            if (System.currentTimeMillis() > endTimeMillis) {
                try {
                    thread.stop();
                } catch (Exception e) {
                    LOGGER.error(e.getMessage(), e);
                }
                LOGGER.error("Send mail unsuccessful");
            } else if (sendEmail.getFinish()) {
                break;
            }

            try {
                Thread.sleep(100);
                Thread.yield();
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }

        }
    }

    @Override
    protected void execute() {
        try {
            Properties mailServerProperties;
            Session getMailSession;
            MimeMessage mailMessage;

            // Step1: setup Mail Server
            mailServerProperties = System.getProperties();
            mailServerProperties.put("mail.smtp.port", "587");
            mailServerProperties.put("mail.smtp.auth", "true");
            mailServerProperties.put("mail.smtp.starttls.enable", "true");

            // Step2: get Mail Session
            getMailSession = Session.getDefaultInstance(mailServerProperties, null);
            mailMessage = new MimeMessage(getMailSession);
            mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(this.emailTo));
            mailMessage.setSubject(this.subject);
            mailMessage.setText(this.content);

            // Step3: Send mail
            Transport transport = getMailSession.getTransport("smtp");

            transport.connect("smtp.gmail.com", USERNAME, PASSWORD);
            transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}