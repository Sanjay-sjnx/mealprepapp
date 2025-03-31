package com.example.mealprepapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "recipes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String description;
    private String instructions;
    private Integer prepTimeMinutes;
    private Integer cookTimeMinutes;
    private Integer servings;
    
    @ManyToMany
    @JoinTable(
        name = "recipe_ingredients",
        joinColumns = @JoinColumn(name = "recipe_id"),
        inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private Set<Ingredient> ingredients = new HashSet<>();
    
    @ElementCollection
    @CollectionTable(name = "recipe_nutrition", joinColumns = @JoinColumn(name = "recipe_id"))
    private List<NutritionalInfo> nutritionalInfo = new ArrayList<>();
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    private String cuisineType;
    private String mealType; // breakfast, lunch, dinner, snack
    private String dietaryType; // vegetarian, vegan, gluten-free, etc.
    
    @Column(name = "image_url")
    private String imageUrl;
    
    @Column(name = "is_featured")
    private boolean featured;
    
    private Integer calories;
    private Double protein; // in grams
    private Double carbs; // in grams
    private Double fat; // in grams
    
    // Helper method to add an ingredient and maintain the bidirectional relationship
    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
        ingredient.getRecipes().add(this);
    }
    
    // Helper method to remove an ingredient and maintain the bidirectional relationship
    public void removeIngredient(Ingredient ingredient) {
        ingredients.remove(ingredient);
        ingredient.getRecipes().remove(this);
    }
} 