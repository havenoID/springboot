package com.springboot.learn.statemachine.config;

import com.springboot.learn.model.Order;
import com.springboot.learn.statemachine.event.OrderStatusChangeEventEnum;
import com.springboot.learn.statemachine.state.OrderStatusEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.statemachine.support.DefaultStateMachineContext;

import javax.annotation.Resource;
import java.util.EnumSet;

/**
 * @author: limin0828
 * Date: 2020-09-08
 */

@Configuration
@EnableStateMachine(name = "orderStateMachine")
public class OrderStateMachineConfig extends EnumStateMachineConfigurerAdapter<OrderStatusEnum, OrderStatusChangeEventEnum> {

    @Resource(name = "payAction")
    private Action<OrderStatusEnum, OrderStatusChangeEventEnum> payAction;

    @Resource(name = "deliverAction")
    private Action<OrderStatusEnum, OrderStatusChangeEventEnum> deliverAction;

    @Resource(name = "receiveAction")
    private Action<OrderStatusEnum, OrderStatusChangeEventEnum> receiveAction;

    /**
     * 配置状态
     * @param states
     * @throws Exception
     */
    @Override
    public void configure(StateMachineStateConfigurer<OrderStatusEnum, OrderStatusChangeEventEnum> states) throws Exception {
        states
                .withStates()
                .initial(OrderStatusEnum.WAIT_PAYMENT)
                .states(EnumSet.allOf(OrderStatusEnum.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStatusEnum, OrderStatusChangeEventEnum> transitions) throws Exception {
        transitions
                .withExternal()
                .source(OrderStatusEnum.WAIT_PAYMENT)
                .target(OrderStatusEnum.WAIT_DELIVER)
                .event(OrderStatusChangeEventEnum.PAYED)
                .action(payAction)

                .and()
                .withExternal()
                .source(OrderStatusEnum.WAIT_DELIVER)
                .target(OrderStatusEnum.WAIT_RECEIVE)
                .event(OrderStatusChangeEventEnum.DELIVERY)
                .action(deliverAction)

                .and()
                .withExternal()
                .source(OrderStatusEnum.WAIT_RECEIVE)
                .target(OrderStatusEnum.FINISH)
                .event(OrderStatusChangeEventEnum.RECEIVED)
                .action(receiveAction);
    }

    @Bean
    public StateMachinePersister<OrderStatusEnum, OrderStatusChangeEventEnum, Order> persister() {
        return new DefaultStateMachinePersister<>(new StateMachinePersist<OrderStatusEnum, OrderStatusChangeEventEnum, Order>() {
            @Override
            public void write(StateMachineContext<OrderStatusEnum, OrderStatusChangeEventEnum> context, Order order) throws Exception {
                //此处并没有进行持久化操作
            }

            @Override
            public StateMachineContext<OrderStatusEnum, OrderStatusChangeEventEnum> read(Order order) throws Exception {
                //此处直接获取order中的状态，其实并没有进行持久化读取操作
                return new DefaultStateMachineContext<>(order.getOrderStatusEnum(), null, null, null);
            }
        });
    }
}
