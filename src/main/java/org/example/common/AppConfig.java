package org.example.common;

import lombok.Setter;
import org.example.infrastructure.temporal.activity.CompleteOrderActivity;
import org.example.infrastructure.temporal.orchestrator.WorkflowOrchestrator;
import org.example.infrastructure.temporal.orchestrator.WorkflowOrchestratorClient;
import org.example.infrastructure.temporal.orchestrator.WorkflowOrchestratorImpl;
import org.example.infrastructure.temporal.worker.OrderWorker;
import org.example.infrastructure.temporal.workflow.activity.impl.CompleteOrderActivityImpl;
import org.example.order.command.OrderCommand;
import org.example.order.command.OrderCommandImpl;
import org.example.order.factory.OrderFactory;
import org.example.order.factory.OrderFactoryImpl;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Setter
public class AppConfig {

    @Bean
    public OrderFactory orderFactory() {
        return new OrderFactoryImpl(orderCommand());
    }

    @Bean
    public OrderCommand orderCommand() {
        return new OrderCommandImpl(workflowOrchestrator());
    }

    @Bean
    public OrderWorker orderWorker() {
        return new OrderWorker(createPendingOrderActivity(), workflowOrchestratorClient());
    }

    @Bean
    public CompleteOrderActivity createPendingOrderActivity() {
        return new CompleteOrderActivityImpl();
    }

    @Bean
    @ConfigurationProperties
    public ApplicationProperties applicationProperties() {
        return new ApplicationProperties();
    }

    @Bean
    public WorkflowOrchestratorClient workflowOrchestratorClient() {
        return new WorkflowOrchestratorClient(applicationProperties());
    }

    @Bean
    public WorkflowOrchestrator workflowOrchestrator() {
        return new WorkflowOrchestratorImpl(workflowOrchestratorClient(), applicationProperties());
    }
}
