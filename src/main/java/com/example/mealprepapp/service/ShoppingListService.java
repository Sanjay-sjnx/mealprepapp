package com.example.mealprepapp.service;

import com.example.mealprepapp.entity.*;
import com.example.mealprepapp.repository.MealPlanRepository;
import com.example.mealprepapp.repository.ShoppingListRepository;
import com.example.mealprepapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
public class ShoppingListService {

    @Autowired
    private ShoppingListRepository shoppingListRepository;

    @Autowired
    private MealPlanRepository mealPlanRepository;

    @Autowired
    private UserRepository userRepository;

    public ShoppingList createShoppingList(ShoppingList shoppingList) {
        return shoppingListRepository.save(shoppingList);
    }

    public Optional<ShoppingList> getShoppingListById(Long id) {
        return shoppingListRepository.findById(id);
    }

    public Page<ShoppingList> getShoppingListsByUser(Long userId, int page, int size) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Pageable pageable = PageRequest.of(page, size);
        return shoppingListRepository.findByUser(user, pageable);
    }

    @Transactional
    public ShoppingList generateShoppingListFromMealPlan(Long mealPlanId) {
        MealPlan mealPlan = mealPlanRepository.findById(mealPlanId)
                .orElseThrow(() -> new IllegalArgumentException("Meal plan not found"));
        
        // Create a new shopping list
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setName("Shopping list for " + mealPlan.getName());
        shoppingList.setUser(mealPlan.getUser());
        shoppingList.setMealPlan(mealPlan);
        shoppingList.setCreatedDate(LocalDate.now());
        shoppingList.setCompleted(false);
        
        // Save the shopping list first to get an ID
        shoppingList = shoppingListRepository.save(shoppingList);
        
        // Collect all ingredients from the meal plan's recipes
        Map<Ingredient, Double> ingredientQuantities = new HashMap<>();
        
        for (PlannedMeal plannedMeal : mealPlan.getPlannedMeals()) {
            Recipe recipe = plannedMeal.getRecipe();
            if (recipe != null) {
                int servings = plannedMeal.getServings();
                double servingRatio = (double) servings / recipe.getServings();
                
                for (Ingredient ingredient : recipe.getIngredients()) {
                    // Accumulate quantities for the same ingredients
                    ingredientQuantities.merge(ingredient, 1.0 * servingRatio, Double::sum);
                }
            }
        }
        
        // Create shopping items from the collected ingredients
        for (Map.Entry<Ingredient, Double> entry : ingredientQuantities.entrySet()) {
            Ingredient ingredient = entry.getKey();
            Double quantity = entry.getValue();
            
            ShoppingItem item = new ShoppingItem();
            item.setIngredient(ingredient);
            item.setQuantity(quantity);
            item.setUnit(ingredient.getUnit()); // Use the ingredient's unit
            item.setPurchased(false);
            item.setCategory(ingredient.getFoodGroup()); // Use the ingredient's food group
            
            // Add to the shopping list using the helper method
            shoppingList.addItem(item);
        }
        
        // Save again with the items
        return shoppingListRepository.save(shoppingList);
    }

    @Transactional
    public ShoppingList updateShoppingList(Long id, ShoppingList shoppingListDetails) {
        ShoppingList shoppingList = shoppingListRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Shopping list not found"));
        
        shoppingList.setName(shoppingListDetails.getName());
        
        // Clear and add all items to maintain the bidirectional relationship
        shoppingList.getItems().clear();
        for (ShoppingItem item : shoppingListDetails.getItems()) {
            shoppingList.addItem(item);
        }
        
        shoppingList.setCompleted(shoppingListDetails.isCompleted());
        
        return shoppingListRepository.save(shoppingList);
    }

    public ShoppingList markShoppingListAsCompleted(Long id) {
        ShoppingList shoppingList = shoppingListRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Shopping list not found"));
        
        shoppingList.setCompleted(true);
        
        return shoppingListRepository.save(shoppingList);
    }

    public void deleteShoppingList(Long id) {
        ShoppingList shoppingList = shoppingListRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Shopping list not found"));
        shoppingListRepository.delete(shoppingList);
    }
} 