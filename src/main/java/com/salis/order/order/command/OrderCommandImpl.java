package com.salis.order.order.command;

import com.salis.order.domain.model.Order;
import com.salis.order.domain.model.OrderStatus;
import com.salis.order.infrastructure.temporal.orchestrator.WorkflowOrchestrator;
import com.salis.order.persistance.OrderRepository;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;
import java.time.Instant;

@RequiredArgsConstructor
public class OrderCommandImpl implements OrderCommand {

    private final OrderRepository orderRepository;
    private final WorkflowOrchestrator workflowOrchestrator;


    @Override
    public Order createOrder(Order order) {
        order.setStatus(OrderStatus.PENDING.name());
        order.setCreatedAt(Timestamp.from(Instant.now()));
        order.setUpdatedAt(Timestamp.from(Instant.now()));
        Order persistedOrder = orderRepository.save(order);
        workflowOrchestrator.createOrder(persistedOrder);
        return persistedOrder;
    }
}
