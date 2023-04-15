package com.salis.order.persistance;

import com.salis.order.domain.model.Order;

public interface OrderRepository {

    Order save(Order order);

    Order get(Long orderId);
}
