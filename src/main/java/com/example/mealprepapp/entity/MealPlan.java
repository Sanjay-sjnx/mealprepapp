package com.example.mealprepapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "meal_plans")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    private LocalDate startDate;
    private LocalDate endDate;
    
    @OneToMany(mappedBy = "mealPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlannedMeal> plannedMeals = new ArrayList<>();
    
    private Integer totalCalories;
    
    @Column(name = "is_active")
    private boolean active;
    
    @Column(name = "created_at")
    private LocalDate createdAt;
    
    @Column(name = "updated_at")
    private LocalDate updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDate.now();
        updatedAt = LocalDate.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDate.now();
    }
    
    // Helper method to add a planned meal and maintain the bidirectional relationship
    public void addPlannedMeal(PlannedMeal plannedMeal) {
        plannedMeals.add(plannedMeal);
        plannedMeal.setMealPlan(this);
    }
    
    // Helper method to remove a planned meal and maintain the bidirectional relationship
    public void removePlannedMeal(PlannedMeal plannedMeal) {
        plannedMeals.remove(plannedMeal);
        plannedMeal.setMealPlan(null);
    }
} 