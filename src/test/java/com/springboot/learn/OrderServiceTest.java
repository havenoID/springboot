package com.springboot.learn;

import com.springboot.learn.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: limin0828
 * Date: 2020-09-09
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void test() {
        orderService.create();
        orderService.pay(1);
        orderService.deliver(1);
        orderService.receive(1);
    }
}
