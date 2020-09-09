package com.springboot.learn.service;

/**
 * @author: limin0828
 * Date: 2020-09-08
 */
public interface OrderService {
    void create();

    void pay(int id);

    void deliver(int id);

    void receive(int id);
}
