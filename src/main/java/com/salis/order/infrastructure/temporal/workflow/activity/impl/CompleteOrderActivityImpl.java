package com.salis.order.infrastructure.temporal.workflow.activity.impl;

import com.salis.order.domain.model.Order;
import com.salis.order.domain.model.OrderStatus;
import com.salis.order.infrastructure.temporal.activity.CompleteOrderActivity;
import com.salis.order.persistance.OrderRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CompleteOrderActivityImpl implements CompleteOrderActivity {

    private final OrderRepository orderRepository;

    @Override
    public void completeOrder(Order order) {
        System.out.println("marking order as completed" + order.getReferenceId());
        order.setStatus(OrderStatus.COMPLETED.name());
        var completedOrder = orderRepository.save(order);
        System.out.println("order completed " + completedOrder.getReferenceId());
    }
}
