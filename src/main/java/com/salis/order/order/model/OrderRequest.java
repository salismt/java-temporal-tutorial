package com.salis.order.order.model;

import lombok.Data;

@Data
public class OrderRequest {

    private String referenceId;
    private Double price;
    private Integer quantity;
    private String description;
}
