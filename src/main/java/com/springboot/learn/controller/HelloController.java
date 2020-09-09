package com.springboot.learn.controller;

import com.springboot.learn.model.User;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @RestController 的效果等效于 @Controller + @ResponseBody
 */
@RestController
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private User user;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ApiOperation(value="spring boot example", notes="test")
    public String hello() {
        return "WelCome to Spring Boot World";
    }

    @ApiOperation(value="get user", notes="get user info")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User getUser(){
        logger.info("get user");
        return user;
    }
}
