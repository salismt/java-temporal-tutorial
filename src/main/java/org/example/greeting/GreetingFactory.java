package org.example.greeting;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.worker.Worker;
import org.example.greeting.activity.GreetingActivityImpl;
import org.example.greeting.workflow.GreetingWorkflow;
import org.example.greeting.workflow.GreetingWorkflowImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class GreetingFactory {

    static final String WORKFLOW_ID = "GreetingWorfklowV1";
    static final String TASK_QUEUE = "GreetingQueue";

    public static void registerGreetingWorkflow(Worker worker) {
        worker.registerWorkflowImplementationTypes(GreetingWorkflowImpl.class);
        worker.registerActivitiesImplementations(new GreetingActivityImpl());
    }


    @Bean
    public static GreetingWorkflow buildGreetingWorkflow(WorkflowClient client) {
        return client.newWorkflowStub(
                GreetingWorkflow.class,
                WorkflowOptions.newBuilder()
                        .setWorkflowId(WORKFLOW_ID)
                        .setTaskQueue(TASK_QUEUE)
                        .build());
    }
}
