package org.example.common;

import io.temporal.client.WorkflowClient;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import org.example.booking.BookingFactory;
import org.example.greeting.GreetingFactory;
import org.example.greeting.activity.GreetingActivityImpl;
import org.example.greeting.workflow.GreetingWorkflowImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class WorkerFactoryConfiguration {

    @Autowired
    private WorkflowClient client;

    @Bean
    public WorkerFactory registerWorkerFactory() {
        WorkerFactory factory = WorkerFactory.newInstance(client);
        Worker worker = factory.newWorker("CommonWorker");
        GreetingFactory.registerGreetingWorkflow(worker);
        BookingFactory.registerBookingWorkflow(worker);
        factory.start();
        return factory;
    }
}
