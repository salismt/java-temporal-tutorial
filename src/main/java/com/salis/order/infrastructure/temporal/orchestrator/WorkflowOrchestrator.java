package com.salis.order.infrastructure.temporal.orchestrator;

import com.salis.order.domain.model.Order;

public interface WorkflowOrchestrator {

    void createOrder(Order order);

}
