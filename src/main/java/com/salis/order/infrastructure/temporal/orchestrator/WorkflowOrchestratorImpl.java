package com.salis.order.infrastructure.temporal.orchestrator;

import com.salis.order.common.TaskQueue;
import com.salis.order.infrastructure.temporal.workflow.OrderFulfillmentWorkflow;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import lombok.RequiredArgsConstructor;
import com.salis.order.common.ApplicationProperties;

import java.util.UUID;

@RequiredArgsConstructor
public class WorkflowOrchestratorImpl implements WorkflowOrchestrator {

    private final WorkflowOrchestratorClient workflowOrchestratorClient;
    private final ApplicationProperties applicationProperties;

    @Override
    public void createOrder(String input) {

        WorkflowClient workflowClient = workflowOrchestratorClient.getWorkflowClient();
        OrderFulfillmentWorkflow orderFulfillmentWorkflow = workflowClient.newWorkflowStub(
                OrderFulfillmentWorkflow.class,
                WorkflowOptions.newBuilder()
                        .setWorkflowId(applicationProperties.getWorkflowId() + "-" + UUID.randomUUID())
                        .setTaskQueue(TaskQueue.ORDER_FULFILLMENT_WORKFLOW_TASK_QUEUE.name())
                        .build());

        // Execute Sync
        orderFulfillmentWorkflow.createOrder(input);
        // Async execution
//        WorkflowClient.start(orderFulfillmentWorkflow::createOrder, input);
    }
}
