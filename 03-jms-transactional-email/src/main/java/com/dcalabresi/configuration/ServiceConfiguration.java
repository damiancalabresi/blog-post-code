package com.dcalabresi.configuration;

import com.dcalabresi.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@Import(MailConfiguration.class)
public class ServiceConfiguration {

    Logger logger = LoggerFactory.getLogger(ServiceConfiguration.class);

    @Bean
    public EmailService emailService() {
        return new EmailService();
    }

}
