package com.dcalabresi.configuration;

import com.dcalabresi.aop.log.LoggingAspect;
import com.dcalabresi.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class ServiceConfiguration {

    Logger logger = LoggerFactory.getLogger(ServiceConfiguration.class);

    @Bean
    public LoggingAspect loggingAspect() {
        return new LoggingAspect();
    }

    @Bean
    public TestService testService() {
        return new TestService();
    }

}
