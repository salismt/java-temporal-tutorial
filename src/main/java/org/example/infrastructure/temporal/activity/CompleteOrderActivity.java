package org.example.infrastructure.temporal.activity;

import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface CompleteOrderActivity {

    void completeOrder(String input);
}
