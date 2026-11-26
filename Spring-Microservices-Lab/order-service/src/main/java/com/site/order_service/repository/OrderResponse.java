package com.site.orderservice.dto;

import com.site.orderservice.model.Order;

public class OrderResponse {

    private Long id;

    private String productName;

    private int quantity;

    private double price;

    private String customerId;

    private CustomerResponse customer;

    public OrderResponse() {
    }

    public OrderResponse(Order order, CustomerResponse customer) {

        this.id = order.getId();
        this.productName = order.getProductName();
        this.quantity = order.getQuantity();
        this.price = order.getPrice();
        this.customerId = order.getCustomerId();
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getCustomerId() {
        return customerId;
    }

    public CustomerResponse getCustomer() {
        return customer;
    }
}