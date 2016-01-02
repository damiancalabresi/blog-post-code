package com.dcalabresi;

import com.dcalabresi.configuration.RestConfiguration;
import com.dcalabresi.filter.IpLimitFilter;
import com.dcalabresi.filter.IpTimeWindowManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@EnableAutoConfiguration
@Import({RestConfiguration.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    @Scope(value="singleton")
    @Order(Ordered.LOWEST_PRECEDENCE)
    public IpTimeWindowManager ipTimeWindowManager() {
        return new IpTimeWindowManager();
    }

    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE)
    public IpLimitFilter ipLimitFilter(IpTimeWindowManager ipTimeWindowManager) {
        return new IpLimitFilter(ipTimeWindowManager);
    }

    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE)
    public FilterRegistrationBean ipLimitFilterRegistrationBean(IpLimitFilter ipLimitFilter) {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(ipLimitFilter);
        registrationBean.setOrder(Ordered.LOWEST_PRECEDENCE);
        return registrationBean;
    }

}
