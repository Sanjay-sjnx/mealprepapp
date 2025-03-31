package com.example.mealprepapp.repository;

import com.example.mealprepapp.entity.MealPlan;
import com.example.mealprepapp.entity.PlannedMeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PlannedMealRepository extends JpaRepository<PlannedMeal, Long> {
    List<PlannedMeal> findByMealPlan(MealPlan mealPlan);
    List<PlannedMeal> findByMealPlanId(Long mealPlanId);
    List<PlannedMeal> findByDate(LocalDate date);
} 