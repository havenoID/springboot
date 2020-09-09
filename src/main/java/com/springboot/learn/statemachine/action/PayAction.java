package com.springboot.learn.statemachine.action;

import com.springboot.learn.statemachine.event.OrderStatusChangeEventEnum;
import com.springboot.learn.statemachine.state.OrderStatusEnum;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

/**
 * @author: limin0828
 * Date: 2020-09-08
 */
@Component("payAction")
public class PayAction implements Action<OrderStatusEnum, OrderStatusChangeEventEnum> {
    @Override
    public void execute(StateContext<OrderStatusEnum, OrderStatusChangeEventEnum> stateContext) {
        System.out.println("payAction execute");
        System.out.println(stateContext.getSource());
    }
}
