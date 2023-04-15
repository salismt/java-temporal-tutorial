package com.salis.order.infrastructure.temporal.worker;

import com.salis.order.infrastructure.temporal.activity.CompleteOrderActivity;
import io.temporal.worker.WorkerFactory;
import io.temporal.worker.WorkerOptions;
import io.temporal.worker.WorkflowImplementationOptions;
import lombok.RequiredArgsConstructor;
import com.salis.order.common.TaskQueue;
import com.salis.order.infrastructure.temporal.orchestrator.WorkflowOrchestratorClient;
import com.salis.order.infrastructure.temporal.workflow.impl.OrderFulfillmentWorkflowImpl;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
public class OrderWorker {

    private final CompleteOrderActivity completeOrderActivity;

    private final WorkflowOrchestratorClient workflowOrchestratorClient;

    @PostConstruct
    public void createWorker() {
        System.out.println("Registering worker..");
        var workerOptions = WorkerOptions.newBuilder().build();
        var workflowClient = workflowOrchestratorClient.getWorkflowClient();
        WorkflowImplementationOptions workflowImplementationOptions =
                WorkflowImplementationOptions.newBuilder()
                        .setFailWorkflowExceptionTypes(NullPointerException.class)
                        .build();

        var workerFactory = WorkerFactory.newInstance(workflowClient);

        var worker =
                workerFactory.newWorker(
                        TaskQueue.ORDER_FULFILLMENT_WORKFLOW_TASK_QUEUE.name(), workerOptions);

        // Can be called multiple times
        worker.registerWorkflowImplementationTypes(
                workflowImplementationOptions, OrderFulfillmentWorkflowImpl.class);

        worker.registerActivitiesImplementations(completeOrderActivity);

        workerFactory.start();

        System.out.println("Registering order worker..");
    }

}
