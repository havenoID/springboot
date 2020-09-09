package com.springboot.learn.statemachine.state;

/**
 * @author: limin0828
 * Date: 2020-09-08
 */
public enum OrderStatusEnum {
    // 待支付，待发货，待收货，订单结束
    WAIT_PAYMENT, WAIT_DELIVER, WAIT_RECEIVE, FINISH;
}
