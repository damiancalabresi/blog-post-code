package com.dcalabresi.configuration;

import com.dcalabresi.rest.TestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ServiceConfiguration.class)
public class RestConfiguration {

    Logger logger = LoggerFactory.getLogger(RestConfiguration.class);

    @Bean
    public TestController testController() {
        return new TestController();
    }

}
