package org.example.common;

import io.temporal.client.WorkflowClient;
import io.temporal.worker.WorkerFactory;
import org.example.greeting.GreetingFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CommonWorkerFactory {

    @Bean
    public static WorkerFactory registerWorkerFactory(WorkflowClient client) {
        WorkerFactory factory = WorkerFactory.newInstance(client);

        GreetingFactory.registerGreetingWorkflow(factory);
        factory.start();
        return factory;
    }
}
