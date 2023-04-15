package com.salis.order.persistance.jpa;

import com.salis.order.persistance.repository.OrderPersistable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<OrderPersistable, Long> {
}
