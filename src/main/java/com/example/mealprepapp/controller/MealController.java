package com.example.mealprepapp.controller;

import com.example.mealprepapp.model.Meal;
import com.example.mealprepapp.service.MealService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import java.util.List;

@RestController
@RequestMapping("/api/meals")
public class MealController {

    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping
    @Operation(summary = "Get all meals with pagination and sorting")
    public ResponseEntity<Page<Meal>> getAllMeals(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "ASC") String direction) {
        return ResponseEntity.ok(mealService.getAllMealsPaginated(page, size, sortBy, direction));
    }

    @GetMapping("/category/{category}")
    @Operation(summary = "Get meals by category with pagination")
    public ResponseEntity<Page<Meal>> getMealsByCategory(
            @PathVariable String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy) {
        return ResponseEntity.ok(mealService.getMealsByCategory(category, page, size, sortBy));
    }

    @GetMapping("/search/calories")
    @Operation(summary = "Find meals in calorie range using JPQL")
    public ResponseEntity<List<Meal>> findMealsInCalorieRange(
            @RequestParam Integer minCalories,
            @RequestParam Integer maxCalories) {
        return ResponseEntity.ok(mealService.findMealsInCalorieRange(minCalories, maxCalories));
    }

    @GetMapping("/search")
    @Operation(summary = "Search meals by name with pagination")
    public ResponseEntity<Page<Meal>> searchMeals(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(mealService.searchMealsByName(keyword, page, size));
    }

    @GetMapping("/search/category-calories")
    @Operation(summary = "Find meals by category and max calories with pagination")
    public ResponseEntity<Page<Meal>> findByCategoryAndMaxCalories(
            @RequestParam String category,
            @RequestParam Integer maxCalories,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "calories") String sortBy) {
        return ResponseEntity.ok(mealService.findByCategoryAndMaxCalories(
            category, maxCalories, page, size, sortBy));
    }

    @GetMapping("/search/low-calorie")
    @Operation(summary = "Find low calorie meals using native query")
    public ResponseEntity<List<Meal>> findLowCalorieMeals(
            @RequestParam Integer maxCalories) {
        return ResponseEntity.ok(mealService.findLowCalorieMeals(maxCalories));
    }
}