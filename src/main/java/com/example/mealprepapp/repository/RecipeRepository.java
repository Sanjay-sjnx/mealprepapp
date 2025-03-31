package com.example.mealprepapp.repository;

import com.example.mealprepapp.entity.Recipe;
import com.example.mealprepapp.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Page<Recipe> findByUser(User user, Pageable pageable);
    Page<Recipe> findByNameContainingIgnoreCase(String keyword, Pageable pageable);
    Page<Recipe> findByDietaryTypeIgnoreCase(String dietaryType, Pageable pageable);
    Page<Recipe> findByMealTypeIgnoreCase(String mealType, Pageable pageable);
} 