package com.salis.order.infrastructure.temporal.workflow;

import com.salis.order.domain.model.Order;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface OrderFulfillmentWorkflow {

    @WorkflowMethod
    void createOrder(Order order);
}
