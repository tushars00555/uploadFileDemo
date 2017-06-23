package com.srajan.config;



import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Bean;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;



/**
 * Created by tushar on 4/4/17.
 */
@Configuration
public class UtilConfig {
    @Bean
    public Logger getLogger() {
        return Logger.getLogger(getClass().getName());
    }


    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
            }
        };
    }

}
