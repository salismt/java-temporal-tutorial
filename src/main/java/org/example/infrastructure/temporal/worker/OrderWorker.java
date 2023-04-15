package org.example.infrastructure.temporal.worker;

import io.temporal.worker.WorkerFactory;
import io.temporal.worker.WorkerOptions;
import io.temporal.worker.WorkflowImplementationOptions;
import lombok.RequiredArgsConstructor;
import org.example.common.TaskQueue;
import org.example.infrastructure.temporal.activity.CompleteOrderActivity;
import org.example.infrastructure.temporal.orchestrator.WorkflowOrchestratorClient;
import org.example.infrastructure.temporal.workflow.impl.OrderFulfillmentWorkflowImpl;

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
