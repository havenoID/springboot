package com.springboot.learn.statemachine.event;

/**
 * @author: limin0828
 * Date: 2020-09-08
 */
public enum OrderStatusChangeEventEnum {
    // 支付，发货，确认收货
    PAYED, DELIVERY, RECEIVED;
}
