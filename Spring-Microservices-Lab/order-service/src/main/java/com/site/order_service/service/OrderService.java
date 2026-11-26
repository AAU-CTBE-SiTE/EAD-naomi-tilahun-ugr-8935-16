package com.site.orderservice.service;

import com.site.orderservice.dto.CustomerResponse;
import com.site.orderservice.dto.OrderResponse;
import com.site.orderservice.model.Order;
import com.site.orderservice.repository.OrderRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final RestClient restClient;

    public OrderService(
            OrderRepository orderRepository,
            RestClient restClient) {

        this.orderRepository = orderRepository;
        this.restClient = restClient;
    }

    // Save order after checking customer exists
    public Order saveOrder(Order order) {

        getCustomerById(order.getCustomerId());

        return orderRepository.save(order);
    }

    // Get customer from Customer Service
    public CustomerResponse getCustomerById(String customerId) {

        try {

            return restClient.get()
                    .uri("/customers/{id}", customerId)
                    .retrieve()
                    .body(CustomerResponse.class);

        } catch (RestClientException e) {

            throw new RuntimeException(
                    "Unable to find customer with ID: " + customerId);
        }
    }

    // Get order by ID with customer information
    public OrderResponse getOrderById(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Order not found"));

        CustomerResponse customer =
                getCustomerById(order.getCustomerId());

        return new OrderResponse(order, customer);
    }

    // Get all orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Get orders by customer ID
    public List<Order> getOrdersByCustomerId(String customerId) {

        getCustomerById(customerId);

        return orderRepository.findByCustomerId(customerId);
    }

    // Update order
    public Order updateOrder(Long id, Order updatedOrder) {

        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Order not found"));

        getCustomerById(updatedOrder.getCustomerId());

        existingOrder.setProductName(updatedOrder.getProductName());
        existingOrder.setQuantity(updatedOrder.getQuantity());
        existingOrder.setPrice(updatedOrder.getPrice());
        existingOrder.setCustomerId(updatedOrder.getCustomerId());

        return orderRepository.save(existingOrder);
    }

    // Delete order
    public void deleteOrder(Long id) {

        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order not found");
        }

        orderRepository.deleteById(id);
    }
}