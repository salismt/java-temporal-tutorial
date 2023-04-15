package com.salis.order.domain.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Order {
    private Long orderId;
    private String referenceId;
    private String status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Double price;
    private Integer quantity;
    private String description;

}
