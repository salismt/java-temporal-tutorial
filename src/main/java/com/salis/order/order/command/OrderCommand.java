package com.salis.order.order.command;

import com.salis.order.domain.model.Order;

public interface OrderCommand {

    Order createOrder(Order order);
}
