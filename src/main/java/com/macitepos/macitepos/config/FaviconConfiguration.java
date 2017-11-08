//package com.macitepos.macitepos.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.Resource;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
//import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
//
//import java.util.Arrays;
//import java.util.Collections;
//
//@Configuration
//public class FaviconConfiguration {
//
//    @Bean
//    public SimpleUrlHandlerMapping simpleUrlHandlerMapping(){
//        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
//        mapping.setOrder(Integer.MIN_VALUE);
//        mapping.setUrlMap(Collections.singletonMap("assets/image/icon.png", faviconRequestHandler()));
//        return mapping;
//    }
//
//    @Autowired
//    ApplicationContext applicationContext;
//
//    @Bean
//    protected ResourceHttpRequestHandler faviconRequestHandler() {
//        ResourceHttpRequestHandler requestHandler = new ResourceHttpRequestHandler();
//        requestHandler.setLocations(Arrays.<Resource> asList(applicationContext.getResource("/")));
//        requestHandler.setCacheSeconds(0);
//        return requestHandler;
//    }
//
//}
