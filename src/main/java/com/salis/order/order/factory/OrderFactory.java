package com.salis.order.order.factory;

import com.salis.order.order.command.OrderCommand;

public interface OrderFactory {

  OrderCommand getOrderCommand();

}
