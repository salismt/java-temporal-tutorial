package org.example.order;

import org.example.order.command.OrderCommand;
import org.example.order.factory.OrderFactory;
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
    public ResponseEntity<String> createOrder(@RequestBody String input) {

        OrderCommand orderCommand = orderFactory.getOrderCommand();
        String result = orderCommand.createOrder(input);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
