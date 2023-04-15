package org.example.infrastructure.temporal.workflow.impl;

import io.temporal.activity.LocalActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;
import org.example.infrastructure.temporal.activity.CompleteOrderActivity;
import org.example.infrastructure.temporal.workflow.OrderFulfillmentWorkflow;

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
    public void createOrder(String input) {

        System.out.println("Completing Order: " + input);
        orderActivity.completeOrder(input);
    }
}
