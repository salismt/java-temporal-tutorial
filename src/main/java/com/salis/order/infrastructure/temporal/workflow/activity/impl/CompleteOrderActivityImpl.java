package com.salis.order.infrastructure.temporal.workflow.activity.impl;

import com.salis.order.infrastructure.temporal.activity.CompleteOrderActivity;

public class CompleteOrderActivityImpl implements CompleteOrderActivity {

    @Override
    public void completeOrder(String input) {
        System.out.println("marking order as completed" + input);
        System.out.println("order completed " + input);
    }
}
