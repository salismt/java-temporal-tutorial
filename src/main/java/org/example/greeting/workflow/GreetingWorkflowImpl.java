package org.example.greeting.workflow;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;
import org.example.greeting.activity.GreetingActivity;

import java.time.Duration;

public class GreetingWorkflowImpl implements GreetingWorkflow {

    private final GreetingActivity activity;

    public GreetingWorkflowImpl() {
        activity = Workflow.newActivityStub(
                GreetingActivity.class,
                ActivityOptions.newBuilder()
                        .setStartToCloseTimeout(Duration.ofSeconds(2))
                        .build());
    }

    @Override
    public String getGreeting(String name) {
        return activity.composeGreeting("Welcome", name);
    }
}
