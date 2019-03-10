package com.springboot.learn;

import com.springboot.learn.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by limin on 19/3/10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BeanLoadTest {

    @Autowired
    private UserService userService;

    @Test
    public void test(){
        System.out.println(userService.getUserName());
    }
}
