package com.salis.order.infrastructure.temporal.workflow;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface OrderFulfillmentWorkflow {

    @WorkflowMethod
    void createOrder(String input);
}
