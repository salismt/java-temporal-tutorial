package org.example.order.command;

import lombok.RequiredArgsConstructor;
import org.example.infrastructure.temporal.orchestrator.WorkflowOrchestrator;

@RequiredArgsConstructor
public class OrderCommandImpl implements OrderCommand {

    private final WorkflowOrchestrator workflowOrchestrator;


    @Override
    public String createOrder(String input) {
        workflowOrchestrator.createOrder(input);
        return "Order has created " + input;
    }
}
