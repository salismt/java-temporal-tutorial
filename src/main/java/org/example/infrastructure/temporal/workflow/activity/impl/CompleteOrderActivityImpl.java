package org.example.infrastructure.temporal.workflow.activity.impl;

import org.example.infrastructure.temporal.activity.CompleteOrderActivity;

public class CompleteOrderActivityImpl implements CompleteOrderActivity {

    @Override
    public void completeOrder(String input) {
        System.out.println("marking order as completed" + input);
        System.out.println("order completed " + input);
    }
}
