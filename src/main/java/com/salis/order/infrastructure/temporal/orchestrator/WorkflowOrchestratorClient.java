package com.salis.order.infrastructure.temporal.orchestrator;

import com.salis.order.common.ApplicationProperties;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WorkflowOrchestratorClient {

    private final ApplicationProperties applicationProperties;

    public WorkflowClient getWorkflowClient() {
        WorkflowServiceStubsOptions options = WorkflowServiceStubsOptions.newBuilder()
                .setTarget(applicationProperties.getTarget())
                .build();
        WorkflowServiceStubs workflowServiceStubs = WorkflowServiceStubs.newServiceStubs(options);
        return WorkflowClient.newInstance(workflowServiceStubs);
    }

}
