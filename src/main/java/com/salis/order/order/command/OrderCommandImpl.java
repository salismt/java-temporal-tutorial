package com.salis.order.order.command;

import com.salis.order.infrastructure.temporal.orchestrator.WorkflowOrchestrator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderCommandImpl implements OrderCommand {

    private final WorkflowOrchestrator workflowOrchestrator;


    @Override
    public String createOrder(String input) {
        workflowOrchestrator.createOrder(input);
        return "Order has created " + input;
    }
}
