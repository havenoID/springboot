package com.springboot.learn.service;

import com.google.common.collect.Maps;
import com.springboot.learn.model.Order;
import com.springboot.learn.statemachine.event.OrderStatusChangeEventEnum;
import com.springboot.learn.statemachine.state.OrderStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author: limin0828
 * Date: 2020-09-08
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private StateMachine<OrderStatusEnum, OrderStatusChangeEventEnum> orderStateMachine;

    @Autowired
    private StateMachinePersister<OrderStatusEnum, OrderStatusChangeEventEnum, Order> orderPersister;

    private int id = 1;
    private Map<Integer, Order> orders = Maps.newHashMap();

    @Override
    public void create() {
        Order order = new Order(id, OrderStatusEnum.WAIT_PAYMENT);
        orders.put(order.getId(), order);
        System.out.println("create order success");
    }

    @Override
    public void pay(int id) {
        Order order = orders.get(id);
        order.setOrderStatusEnum(OrderStatusEnum.WAIT_PAYMENT);
        Message message = MessageBuilder
                .withPayload(OrderStatusChangeEventEnum.PAYED)
                .setHeader("order", order)
                .build();
        sendEvent(message, order);
        System.out.println("order " + order.getId() + "payed success");

    }

    @Override
    public void deliver(int id) {
        Order order = orders.get(id);
        order.setOrderStatusEnum(OrderStatusEnum.WAIT_DELIVER);
        Message message = MessageBuilder
                .withPayload(OrderStatusChangeEventEnum.DELIVERY)
                .setHeader("order", order)
                .build();
        sendEvent(message, order);
        System.out.println("order " + order.getId() + "delivery success");
    }

    @Override
    public void receive(int id) {
        Order order = orders.get(id);
        order.setOrderStatusEnum(OrderStatusEnum.WAIT_RECEIVE);
        Message message = MessageBuilder
                .withPayload(OrderStatusChangeEventEnum.RECEIVED)
                .setHeader("order", order)
                .build();
        sendEvent(message, order);
        System.out.println("order " + order.getId() + "received");
    }

    private synchronized boolean sendEvent(Message<OrderStatusChangeEventEnum> message, Order order) {
        boolean result = false;
        try {
            orderStateMachine.start();
            //尝试恢复状态机状态
            orderPersister.restore(orderStateMachine, order);

            //添加延迟用于线程安全测试
            Thread.sleep(1000);

            result = orderStateMachine.sendEvent(message);
            //持久化状态机状态
            //orderPersister.persist(orderStateMachine, order);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            orderStateMachine.stop();
        }
        return result;
    }
}
