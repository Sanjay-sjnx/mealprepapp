package com.example.mealprepapp.repository;

import com.example.mealprepapp.entity.MealPlan;
import com.example.mealprepapp.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealPlanRepository extends JpaRepository<MealPlan, Long> {
    Page<MealPlan> findByUser(User user, Pageable pageable);
    Page<MealPlan> findByUserAndActiveTrue(User user, Pageable pageable);
} 