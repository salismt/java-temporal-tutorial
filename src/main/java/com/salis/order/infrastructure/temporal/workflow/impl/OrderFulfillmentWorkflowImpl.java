package com.salis.order.infrastructure.temporal.workflow.impl;

import com.salis.order.domain.model.Order;
import com.salis.order.infrastructure.temporal.activity.CompleteOrderActivity;
import io.temporal.activity.LocalActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;
import com.salis.order.infrastructure.temporal.workflow.OrderFulfillmentWorkflow;

import java.time.Duration;

public class OrderFulfillmentWorkflowImpl implements OrderFulfillmentWorkflow {

    private final LocalActivityOptions localActivityOptions =
            LocalActivityOptions.newBuilder()
                    .setStartToCloseTimeout(Duration.ofMinutes(1))
                    .setRetryOptions(RetryOptions.newBuilder().setMaximumAttempts(10).build())
                    .build();

    private final CompleteOrderActivity orderActivity =
            Workflow.newLocalActivityStub(CompleteOrderActivity.class, localActivityOptions);

    @Override
    public void createOrder(Order order) {

        System.out.println("Completing Order: " + order.getReferenceId());
        orderActivity.completeOrder(order);
    }
}
