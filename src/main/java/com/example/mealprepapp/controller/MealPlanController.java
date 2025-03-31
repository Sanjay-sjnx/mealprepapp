package com.example.mealprepapp.controller;

import com.example.mealprepapp.entity.MealPlan;
import com.example.mealprepapp.service.MealPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/meal-plans")
public class MealPlanController {

    @Autowired
    private MealPlanService mealPlanService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MealPlan createMealPlan(@RequestBody MealPlan mealPlan) {
        return mealPlanService.createMealPlan(mealPlan);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MealPlan> getMealPlanById(@PathVariable Long id) {
        Optional<MealPlan> mealPlan = mealPlanService.getMealPlanById(id);
        return mealPlan.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public Page<MealPlan> getMealPlansByUser(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return mealPlanService.getMealPlansByUser(userId, page, size);
    }

    @GetMapping("/active/user/{userId}")
    public Page<MealPlan> getActiveMealPlansByUser(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return mealPlanService.getActiveMealPlansByUser(userId, page, size);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MealPlan> updateMealPlan(@PathVariable Long id, @RequestBody MealPlan mealPlan) {
        try {
            MealPlan updatedMealPlan = mealPlanService.updateMealPlan(id, mealPlan);
            return ResponseEntity.ok(updatedMealPlan);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMealPlan(@PathVariable Long id) {
        try {
            mealPlanService.deleteMealPlan(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
} 