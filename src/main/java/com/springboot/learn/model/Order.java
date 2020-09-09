package com.springboot.learn.model;

import com.springboot.learn.statemachine.state.OrderStatusEnum;

/**
 * @author: limin0828
 * Date: 2020-09-09
 */
public class Order {
    private int id;
    private OrderStatusEnum orderStatusEnum;

    public Order(int id, OrderStatusEnum orderStatusEnum) {
        this.id = id;
        this.orderStatusEnum = orderStatusEnum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrderStatusEnum getOrderStatusEnum() {
        return orderStatusEnum;
    }

    public void setOrderStatusEnum(OrderStatusEnum orderStatusEnum) {
        this.orderStatusEnum = orderStatusEnum;
    }
}
