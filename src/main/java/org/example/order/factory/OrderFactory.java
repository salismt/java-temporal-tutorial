package org.example.order.factory;

import org.example.order.command.OrderCommand;

public interface OrderFactory {

  OrderCommand getOrderCommand();

}
