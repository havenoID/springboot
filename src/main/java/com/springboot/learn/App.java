package com.springboot.learn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;


//@ComponentScan(basePackages = {"com.springboot.learn"})
@SpringBootApplication(scanBasePackages = {"com.springboot.learn"})
public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main( String[] args ) {
        SpringApplication.run(App.class, args);
    }
}
