package com.salis.order.persistance;

import com.salis.order.common.ResourceNotFoundException;
import com.salis.order.domain.model.Order;
import com.salis.order.persistance.jpa.OrderJpaRepository;
import com.salis.order.persistance.mapper.OrderPersistableMapper;
import com.salis.order.persistance.repository.OrderPersistable;

import javax.transaction.Transactional;

public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;

    public OrderRepositoryImpl(OrderJpaRepository orderJpaRepository) {
        this.orderJpaRepository = orderJpaRepository;
    }

    @Override
    @Transactional
    public Order save(Order order) {
        System.out.println("Saving order " + order.getReferenceId());
        OrderPersistable orderData = OrderPersistableMapper.MAPPER.map(order);
        orderData = orderJpaRepository.save(orderData);
        System.out.println("Order saved: " + orderData.getId());
        return OrderPersistableMapper.MAPPER.map(orderData);
    }

    @Override
    public Order get(Long orderId) {
        System.out.println("Fetching order id " + orderId);
        OrderPersistable orderData = orderJpaRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        System.out.println("Fetched order " + orderData.getId());
        return OrderPersistableMapper.MAPPER.map(orderData);
    }
}
