package com.salis.order.infrastructure.temporal.orchestrator;

public interface WorkflowOrchestrator {

    void createOrder(String input);

}
