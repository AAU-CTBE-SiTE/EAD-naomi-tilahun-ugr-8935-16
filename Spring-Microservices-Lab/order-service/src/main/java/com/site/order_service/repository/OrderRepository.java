package com.site.orderservice.repository;

import com.site.orderservice.model.Order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository
        extends JpaRepository<Order, Long> {

    List<Order> findByCustomerId(String customerId);

}