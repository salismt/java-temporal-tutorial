package com.salis.order.infrastructure.temporal.activity;

import com.salis.order.domain.model.Order;
import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface CompleteOrderActivity {

    void completeOrder(Order order);
}
