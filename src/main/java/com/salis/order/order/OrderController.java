package com.salis.order.order;

import com.salis.order.domain.model.Order;
import com.salis.order.order.command.OrderCommand;
import com.salis.order.order.factory.OrderFactory;
import com.salis.order.order.model.OrderRequest;
import com.salis.order.order.model.OrderRequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderFactory orderFactory;

    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest request) {

        System.out.println("Creating order for reference id: " + request.getReferenceId());
        Order order = OrderRequestMapper.MAPPER.map(request);
        OrderCommand orderCommand = orderFactory.getOrderCommand();
        Order result = orderCommand.createOrder(order);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
