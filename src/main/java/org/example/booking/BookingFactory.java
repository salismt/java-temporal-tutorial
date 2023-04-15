package org.example.booking;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import org.example.booking.activity.BookingActivityImpl;
import org.example.booking.workflow.BookingWorkflow;
import org.example.booking.workflow.BookingWorkflowImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BookingFactory {

    static final String WORKFLOW_ID = "BookingWorfklowV1";
    static final String TASK_QUEUE = "BookingQueue";

    public static void registerBookingWorkflow(Worker worker) {
        worker.registerWorkflowImplementationTypes(BookingWorkflowImpl.class);
        worker.registerActivitiesImplementations(new BookingActivityImpl());
    }

    @Bean
    public static BookingWorkflow buildBookingWorkflow(WorkflowClient client) {
        return client.newWorkflowStub(BookingWorkflow.class,
                WorkflowOptions.newBuilder()
                        .setWorkflowId(WORKFLOW_ID)
                        .setTaskQueue(TASK_QUEUE)
                        .build());
    }
}
