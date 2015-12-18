package com.dcalabresi.configuration;

import com.dcalabresi.mail.queue.EmailQueuer;
import com.dcalabresi.mail.send.EmailDequeuer;
import com.dcalabresi.mail.send.EmailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@Import({JmsConfiguration.class})
public class MailConfiguration {

    Logger logger = LoggerFactory.getLogger(MailConfiguration.class);

    @Autowired
    EmailDequeuer emailDequeuer;

    @Scheduled(initialDelay = 10000, fixedDelay = 5000)
    public void startSendEnqueuedEmails() {
        emailDequeuer.sendEnqueuedEmails();
    }

    @Bean
    public EmailQueuer emailQueuer() {
        return new EmailQueuer();
    }

    @Bean
    public EmailDequeuer emailDequeuer() {
        return new EmailDequeuer();
    }

    @Bean
    public EmailSender emailSender() {
        return new EmailSender();
    }

}
