package com.example.mealprepapp.repository;

import com.example.mealprepapp.model.Meal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
    
    // Pagination & Sorting (Spring Data JPA methods)
    Page<Meal> findAll(Pageable pageable);
    Page<Meal> findByCategory(String category, Pageable pageable);
    
    // JPQL Queries
    @Query("SELECT m FROM Meal m WHERE m.calories BETWEEN :minCalories AND :maxCalories")
    List<Meal> findMealsInCalorieRange(
        @Param("minCalories") Integer minCalories, 
        @Param("maxCalories") Integer maxCalories
    );
    
    @Query("SELECT m FROM Meal m WHERE LOWER(m.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Meal> searchMealsByName(@Param("keyword") String keyword, Pageable pageable);
    
    @Query("SELECT m FROM Meal m WHERE m.category = :category AND m.calories <= :maxCalories")
    Page<Meal> findByCategoryAndMaxCalories(
        @Param("category") String category, 
        @Param("maxCalories") Integer maxCalories, 
        Pageable pageable
    );

    // Native SQL Query
    @Query(value = "SELECT * FROM meals m WHERE m.calories < :calories ORDER BY m.calories DESC", 
           nativeQuery = true)
    List<Meal> findLowCalorieMeals(@Param("calories") Integer calories);
}