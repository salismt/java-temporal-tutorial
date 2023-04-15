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
import com.salis.order.persistance.OrderRepository;
import com.salis.order.persistance.OrderRepositoryImpl;
import com.salis.order.persistance.jpa.OrderJpaRepository;
import lombok.Setter;
import com.salis.order.infrastructure.temporal.orchestrator.WorkflowOrchestratorClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Setter
public class AppConfig {

    @Bean
    public OrderRepository orderRepository(OrderJpaRepository orderJpaRepository) {
        return new OrderRepositoryImpl(orderJpaRepository);
    }

    @Bean
    public OrderFactory orderFactory(OrderRepository orderRepository) {
        return new OrderFactoryImpl(orderCommand(orderRepository));
    }

    @Bean
    public OrderCommand orderCommand(OrderRepository orderRepository) {
        return new OrderCommandImpl(orderRepository, workflowOrchestrator());
    }

    @Bean
    public OrderWorker orderWorker(OrderRepository orderRepository) {
        return new OrderWorker(createPendingOrderActivity(orderRepository), workflowOrchestratorClient());
    }

    @Bean
    public CompleteOrderActivity createPendingOrderActivity(OrderRepository orderRepository) {
        return new CompleteOrderActivityImpl(orderRepository);
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
