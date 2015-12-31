package com.dcalabresi.configuration;

import com.dcalabresi.faces.ExampleManagedBean;
import com.dcalabresi.rest.TestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class FacesConfiguration {

    Logger logger = LoggerFactory.getLogger(FacesConfiguration.class);

    @Bean
    @Scope("session")
    public ExampleManagedBean exampleManagedBean() {
        return new ExampleManagedBean();
    }

}
