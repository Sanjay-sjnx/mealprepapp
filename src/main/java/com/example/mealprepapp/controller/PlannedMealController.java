package com.example.mealprepapp.controller;

import com.example.mealprepapp.entity.PlannedMeal;
import com.example.mealprepapp.service.PlannedMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/planned-meals")
public class PlannedMealController {

    @Autowired
    private PlannedMealService plannedMealService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlannedMeal createPlannedMeal(@RequestBody PlannedMeal plannedMeal) {
        return plannedMealService.createPlannedMeal(plannedMeal);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlannedMeal> getPlannedMealById(@PathVariable Long id) {
        return plannedMealService.getPlannedMealById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/meal-plan/{mealPlanId}")
    public List<PlannedMeal> getPlannedMealsByMealPlan(@PathVariable Long mealPlanId) {
        return plannedMealService.getPlannedMealsByMealPlanId(mealPlanId);
    }

    @GetMapping("/date/{date}")
    public List<PlannedMeal> getPlannedMealsByDate(@PathVariable String date) {
        LocalDate parsedDate = LocalDate.parse(date);
        return plannedMealService.getPlannedMealsByDate(parsedDate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlannedMeal> updatePlannedMeal(@PathVariable Long id, @RequestBody PlannedMeal plannedMeal) {
        try {
            PlannedMeal updatedPlannedMeal = plannedMealService.updatePlannedMeal(id, plannedMeal);
            return ResponseEntity.ok(updatedPlannedMeal);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/mark-prepared")
    public ResponseEntity<PlannedMeal> markMealAsPrepared(@PathVariable Long id) {
        try {
            PlannedMeal updatedPlannedMeal = plannedMealService.markMealAsPrepared(id);
            return ResponseEntity.ok(updatedPlannedMeal);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/mark-consumed")
    public ResponseEntity<PlannedMeal> markMealAsConsumed(@PathVariable Long id) {
        try {
            PlannedMeal updatedPlannedMeal = plannedMealService.markMealAsConsumed(id);
            return ResponseEntity.ok(updatedPlannedMeal);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlannedMeal(@PathVariable Long id) {
        try {
            plannedMealService.deletePlannedMeal(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
} 