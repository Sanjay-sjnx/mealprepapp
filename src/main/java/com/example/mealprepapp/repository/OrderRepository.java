package com.example.mealprepapp.repository;

import com.example.mealprepapp.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    Page<OrderEntity> findByUserId(Long userId, Pageable pageable);
    Page<OrderEntity> findByRestaurantId(Long restaurantId, Pageable pageable);
}
