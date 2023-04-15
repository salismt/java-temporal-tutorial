package com.salis.order.order.factory;

import com.salis.order.order.command.OrderCommand;

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
