package com.springboot.learn;

import com.alibaba.fastjson.JSON;
import com.springboot.learn.model.User;
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
public class YmlConfigurationTest {

    @Autowired
    private User user;

    @Test
    public void ymlTest(){
        System.out.println(JSON.toJSONString(user));
    }

}
