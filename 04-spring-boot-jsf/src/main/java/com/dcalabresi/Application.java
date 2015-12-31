package com.dcalabresi;

import com.dcalabresi.configuration.FacesConfiguration;
import com.dcalabresi.configuration.RestConfiguration;
import com.dcalabresi.faces.FacesInitializer;
import org.primefaces.webapp.filter.FileUploadFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import javax.faces.webapp.FacesServlet;
import javax.servlet.MultipartConfigElement;

@EnableAutoConfiguration
@Import({RestConfiguration.class, FacesConfiguration.class})
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(new Class[] { Application.class, FacesInitializer.class});
    }

    @Bean
    public FilterRegistrationBean fileUploadFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.addUrlPatterns("*.jsf");
        registrationBean.setFilter(new FileUploadFilter());
        return registrationBean;
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean(MultipartConfigElement multipartConfigElement) {
        FacesServlet servlet = new FacesServlet();
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(servlet, "*.jsf");
        servletRegistrationBean.setMultipartConfig(multipartConfigElement);
        return servletRegistrationBean;
    }

}
