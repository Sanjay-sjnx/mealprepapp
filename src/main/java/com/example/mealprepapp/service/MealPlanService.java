package com.example.mealprepapp.service;

import com.example.mealprepapp.entity.MealPlan;
import com.example.mealprepapp.entity.User;
import com.example.mealprepapp.repository.MealPlanRepository;
import com.example.mealprepapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MealPlanService {

    @Autowired
    private MealPlanRepository mealPlanRepository;

    @Autowired
    private UserRepository userRepository;

    public MealPlan createMealPlan(MealPlan mealPlan) {
        return mealPlanRepository.save(mealPlan);
    }

    public Optional<MealPlan> getMealPlanById(Long id) {
        return mealPlanRepository.findById(id);
    }

    public Page<MealPlan> getMealPlansByUser(Long userId, int page, int size) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Pageable pageable = PageRequest.of(page, size);
        return mealPlanRepository.findByUser(user, pageable);
    }

    public Page<MealPlan> getActiveMealPlansByUser(Long userId, int page, int size) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Pageable pageable = PageRequest.of(page, size);
        return mealPlanRepository.findByUserAndActiveTrue(user, pageable);
    }

    public MealPlan updateMealPlan(Long id, MealPlan mealPlanDetails) {
        MealPlan mealPlan = mealPlanRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Meal plan not found"));
        
        mealPlan.setName(mealPlanDetails.getName());
        mealPlan.setDescription(mealPlanDetails.getDescription());
        mealPlan.setStartDate(mealPlanDetails.getStartDate());
        mealPlan.setEndDate(mealPlanDetails.getEndDate());
        mealPlan.setPlannedMeals(mealPlanDetails.getPlannedMeals());
        mealPlan.setTotalCalories(mealPlanDetails.getTotalCalories());
        mealPlan.setActive(mealPlanDetails.isActive());
        
        return mealPlanRepository.save(mealPlan);
    }

    public void deleteMealPlan(Long id) {
        MealPlan mealPlan = mealPlanRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Meal plan not found"));
        mealPlanRepository.delete(mealPlan);
    }
} 