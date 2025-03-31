package com.example.mealprepapp.service;

import com.example.mealprepapp.model.Meal;
import com.example.mealprepapp.repository.MealRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MealService {

    private final MealRepository mealRepository;

    public MealService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    // Pagination & Sorting methods
    public Page<Meal> getAllMealsPaginated(int page, int size, String sortBy, String direction) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        return mealRepository.findAll(PageRequest.of(page, size, sort));
    }

    public Page<Meal> getMealsByCategory(String category, int page, int size, String sortBy) {
        return mealRepository.findByCategory(category, 
            PageRequest.of(page, size, Sort.by(sortBy).ascending()));
    }

    // JPQL query methods
    public List<Meal> findMealsInCalorieRange(Integer minCalories, Integer maxCalories) {
        return mealRepository.findMealsInCalorieRange(minCalories, maxCalories);
    }

    public Page<Meal> searchMealsByName(String keyword, int page, int size) {
        return mealRepository.searchMealsByName(keyword, PageRequest.of(page, size));
    }

    public Page<Meal> findByCategoryAndMaxCalories(
            String category, 
            Integer maxCalories, 
            int page, 
            int size, 
            String sortBy) {
        return mealRepository.findByCategoryAndMaxCalories(
            category, 
            maxCalories, 
            PageRequest.of(page, size, Sort.by(sortBy))
        );
    }

    // Native query method
    public List<Meal> findLowCalorieMeals(Integer maxCalories) {
        return mealRepository.findLowCalorieMeals(maxCalories);
    }
}