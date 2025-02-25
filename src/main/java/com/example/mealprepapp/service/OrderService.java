package com.example.mealprepapp.service;

import com.example.mealprepapp.entity.OrderEntity;
import com.example.mealprepapp.exception.ResourceNotFoundException;
import com.example.mealprepapp.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public OrderEntity createOrder(OrderEntity order) {
        return orderRepository.save(order);
    }

    public Page<OrderEntity> getOrdersByUser(Long userId, int page, int size) {
        return orderRepository.findByUserId(userId, PageRequest.of(page, size));
    }

    public Page<OrderEntity> getOrdersByRestaurant(Long restaurantId, int page, int size) {
        return orderRepository.findByRestaurantId(restaurantId, PageRequest.of(page, size));
    }

    public OrderEntity getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + orderId));
    }

    public OrderEntity updateOrder(Long orderId, OrderEntity updatedOrder) {
        OrderEntity order = getOrderById(orderId);
        order.setOrderDate(updatedOrder.getOrderDate());
        order.setStatus(updatedOrder.getStatus());
        // Additional updates (user, restaurant, orderItems) can be added as needed.
        return orderRepository.save(order);
    }

    public void deleteOrder(Long orderId) {
        OrderEntity order = getOrderById(orderId);
        orderRepository.delete(order);
    }
}
