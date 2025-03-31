package com.example.mealprepapp.service;

import com.example.mealprepapp.entity.Recipe;
import com.example.mealprepapp.entity.User;
import com.example.mealprepapp.repository.RecipeRepository;
import com.example.mealprepapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserRepository userRepository;

    public Recipe createRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public Optional<Recipe> getRecipeById(Long id) {
        return recipeRepository.findById(id);
    }

    public Page<Recipe> getAllRecipes(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return recipeRepository.findAll(pageable);
    }

    public Page<Recipe> getRecipesByUser(Long userId, int page, int size) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Pageable pageable = PageRequest.of(page, size);
        return recipeRepository.findByUser(user, pageable);
    }

    public Page<Recipe> searchRecipes(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return recipeRepository.findByNameContainingIgnoreCase(keyword, pageable);
    }

    public Page<Recipe> getRecipesByDietaryType(String dietaryType, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return recipeRepository.findByDietaryTypeIgnoreCase(dietaryType, pageable);
    }

    public Page<Recipe> getRecipesByMealType(String mealType, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return recipeRepository.findByMealTypeIgnoreCase(mealType, pageable);
    }

    public Recipe updateRecipe(Long id, Recipe recipeDetails) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Recipe not found"));
        
        recipe.setName(recipeDetails.getName());
        recipe.setDescription(recipeDetails.getDescription());
        recipe.setInstructions(recipeDetails.getInstructions());
        recipe.setPrepTimeMinutes(recipeDetails.getPrepTimeMinutes());
        recipe.setCookTimeMinutes(recipeDetails.getCookTimeMinutes());
        recipe.setServings(recipeDetails.getServings());
        recipe.setIngredients(recipeDetails.getIngredients());
        recipe.setNutritionalInfo(recipeDetails.getNutritionalInfo());
        recipe.setCuisineType(recipeDetails.getCuisineType());
        recipe.setMealType(recipeDetails.getMealType());
        recipe.setDietaryType(recipeDetails.getDietaryType());
        recipe.setImageUrl(recipeDetails.getImageUrl());
        recipe.setFeatured(recipeDetails.isFeatured());
        recipe.setCalories(recipeDetails.getCalories());
        recipe.setProtein(recipeDetails.getProtein());
        recipe.setCarbs(recipeDetails.getCarbs());
        recipe.setFat(recipeDetails.getFat());
        
        return recipeRepository.save(recipe);
    }

    public void deleteRecipe(Long id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Recipe not found"));
        recipeRepository.delete(recipe);
    }
} 