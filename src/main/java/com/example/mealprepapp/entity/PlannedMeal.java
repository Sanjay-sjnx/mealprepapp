package com.example.mealprepapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "planned_meals")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlannedMeal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;
    
    @ManyToOne
    @JoinColumn(name = "meal_plan_id")
    private MealPlan mealPlan;
    
    private LocalDate date;
    
    private String mealType; // breakfast, lunch, dinner, snack
    
    private Integer servings;
    
    @Column(name = "prepared")
    private boolean prepared;
    
    @Column(name = "consumed")
    private boolean consumed;
    
    private String notes;
} 