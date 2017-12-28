//package com.macitepos.macitepos.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//
//@Configuration
//
//public class StaticResourceConfiguration extends WebMvcConfigurerAdapter {
//
//    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
//            "classpath:/META-INF/resources/", "classpath:/resources/",
//            "classpath:/static/", "classpath:/public/" };
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/image/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS).setCachePeriod(0);
//        //registry.addResourceHandler("/resources/**").addResourceLocations("/resources/").setCachePeriod(0);
//    }
//}
