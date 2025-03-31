package com.example.mealprepapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shopping_lists")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "meal_plan_id")
    private MealPlan mealPlan;
    
    @OneToMany(mappedBy = "shoppingList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShoppingItem> items = new ArrayList<>();
    
    private LocalDate createdDate;
    
    @Column(name = "is_completed")
    private boolean completed;
    
    @PrePersist
    protected void onCreate() {
        createdDate = LocalDate.now();
    }
    
    // Helper method to add a shopping item and maintain the bidirectional relationship
    public void addItem(ShoppingItem item) {
        items.add(item);
        item.setShoppingList(this);
    }
    
    // Helper method to remove a shopping item and maintain the bidirectional relationship
    public void removeItem(ShoppingItem item) {
        items.remove(item);
        item.setShoppingList(null);
    }
} 