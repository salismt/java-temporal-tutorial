package org.example.order.factory;

import org.example.order.command.OrderCommand;

public class OrderFactoryImpl implements OrderFactory {

  private final OrderCommand orderCommand;

  public OrderFactoryImpl(OrderCommand orderCommand) {
    this.orderCommand = orderCommand;
  }

  @Override
  public OrderCommand getOrderCommand() {
    return orderCommand;
  }

}
