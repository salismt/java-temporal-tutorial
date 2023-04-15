package com.salis.order.infrastructure.temporal.activity;

import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface CompleteOrderActivity {

    void completeOrder(String input);
}
