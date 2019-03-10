package com.springboot.learn.config;

import com.springboot.learn.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

//@ImportResource(locations = {"classpath*:spring/config/local/appcontext-bean.xml"})
@Configuration
public class Config {

    @Bean
    public UserService userService(){
        return new UserService();
    }
}
