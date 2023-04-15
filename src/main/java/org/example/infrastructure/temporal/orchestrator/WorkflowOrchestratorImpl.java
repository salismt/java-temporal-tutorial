package org.example.infrastructure.temporal.orchestrator;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import lombok.RequiredArgsConstructor;
import org.example.common.ApplicationProperties;
import org.example.common.TaskQueue;
import org.example.infrastructure.temporal.workflow.OrderFulfillmentWorkflow;

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
        WorkflowClient.start(orderFulfillmentWorkflow::createOrder, input);
    }
}
