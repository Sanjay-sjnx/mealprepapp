package com.example.mealprepapp.service;

import com.example.mealprepapp.entity.MealPlan;
import com.example.mealprepapp.entity.PlannedMeal;
import com.example.mealprepapp.repository.MealPlanRepository;
import com.example.mealprepapp.repository.PlannedMealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PlannedMealService {

    @Autowired
    private PlannedMealRepository plannedMealRepository;

    @Autowired
    private MealPlanRepository mealPlanRepository;

    public PlannedMeal createPlannedMeal(PlannedMeal plannedMeal) {
        return plannedMealRepository.save(plannedMeal);
    }

    public Optional<PlannedMeal> getPlannedMealById(Long id) {
        return plannedMealRepository.findById(id);
    }

    public List<PlannedMeal> getPlannedMealsByMealPlanId(Long mealPlanId) {
        MealPlan mealPlan = mealPlanRepository.findById(mealPlanId)
                .orElseThrow(() -> new IllegalArgumentException("Meal plan not found"));
        return plannedMealRepository.findByMealPlan(mealPlan);
    }

    public List<PlannedMeal> getPlannedMealsByDate(LocalDate date) {
        return plannedMealRepository.findByDate(date);
    }

    public PlannedMeal updatePlannedMeal(Long id, PlannedMeal plannedMealDetails) {
        PlannedMeal plannedMeal = plannedMealRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Planned meal not found"));
        
        plannedMeal.setRecipe(plannedMealDetails.getRecipe());
        plannedMeal.setMealPlan(plannedMealDetails.getMealPlan());
        plannedMeal.setDate(plannedMealDetails.getDate());
        plannedMeal.setMealType(plannedMealDetails.getMealType());
        plannedMeal.setServings(plannedMealDetails.getServings());
        plannedMeal.setPrepared(plannedMealDetails.isPrepared());
        plannedMeal.setConsumed(plannedMealDetails.isConsumed());
        plannedMeal.setNotes(plannedMealDetails.getNotes());
        
        return plannedMealRepository.save(plannedMeal);
    }

    public PlannedMeal markMealAsPrepared(Long id) {
        PlannedMeal plannedMeal = plannedMealRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Planned meal not found"));
        
        plannedMeal.setPrepared(true);
        
        return plannedMealRepository.save(plannedMeal);
    }

    public PlannedMeal markMealAsConsumed(Long id) {
        PlannedMeal plannedMeal = plannedMealRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Planned meal not found"));
        
        plannedMeal.setConsumed(true);
        
        return plannedMealRepository.save(plannedMeal);
    }

    public void deletePlannedMeal(Long id) {
        PlannedMeal plannedMeal = plannedMealRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Planned meal not found"));
        plannedMealRepository.delete(plannedMeal);
    }
} 