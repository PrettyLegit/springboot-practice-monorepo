package com.prettylegit.soapcoursemanagement.soap;

import jakarta.servlet.ServletRegistration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

//Enable Spring Web Services
@EnableWs
// Spring Configuration
@Configuration
public class WebServiceConfig {
    // MessageDispatcherServlet
    // ApplicationContext
    // url -> /ws/*

    @Bean
    public ServletRegistration messageDispatcherServlet(ApplicationContext context) {
        MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
        messageDispatcherServlet.setApplicationContext(context);
        messageDispatcherServlet.setTransformWsdlLocations(true);
        return (ServletRegistration) new ServletRegistrationBean(messageDispatcherServlet, "/ws/*");
    }
}
