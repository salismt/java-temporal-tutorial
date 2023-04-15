package com.salis.order.common;

import com.salis.order.infrastructure.temporal.activity.CompleteOrderActivity;
import com.salis.order.infrastructure.temporal.orchestrator.WorkflowOrchestrator;
import com.salis.order.infrastructure.temporal.orchestrator.WorkflowOrchestratorImpl;
import com.salis.order.infrastructure.temporal.worker.OrderWorker;
import com.salis.order.infrastructure.temporal.workflow.activity.impl.CompleteOrderActivityImpl;
import com.salis.order.order.command.OrderCommand;
import com.salis.order.order.command.OrderCommandImpl;
import com.salis.order.order.factory.OrderFactory;
import com.salis.order.order.factory.OrderFactoryImpl;
import lombok.Setter;
import com.salis.order.infrastructure.temporal.orchestrator.WorkflowOrchestratorClient;
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
