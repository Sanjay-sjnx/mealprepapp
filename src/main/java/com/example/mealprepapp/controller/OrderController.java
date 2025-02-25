package com.example.mealprepapp.controller;

import com.example.mealprepapp.entity.OrderEntity;
import com.example.mealprepapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderEntity createOrder(@RequestBody OrderEntity order) {
        return orderService.createOrder(order);
    }

    @GetMapping("/user/{userId}")
    public Page<OrderEntity> getOrdersByUser(@PathVariable Long userId,
                                             @RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size) {
        return orderService.getOrdersByUser(userId, page, size);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public Page<OrderEntity> getOrdersByRestaurant(@PathVariable Long restaurantId,
                                                   @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "10") int size) {
        return orderService.getOrdersByRestaurant(restaurantId, page, size);
    }

    @GetMapping("/{orderId}")
    public OrderEntity getOrderById(@PathVariable Long orderId) {
        return orderService.getOrderById(orderId);
    }

    @PutMapping("/{orderId}")
    public OrderEntity updateOrder(@PathVariable Long orderId, @RequestBody OrderEntity order) {
        return orderService.updateOrder(orderId, order);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
    }
}
