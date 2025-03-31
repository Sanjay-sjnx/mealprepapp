package com.example.mealprepapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "shopping_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;
    
    @ManyToOne
    @JoinColumn(name = "shopping_list_id")
    private ShoppingList shoppingList;
    
    private Double quantity;
    
    private String unit;
    
    @Column(name = "is_purchased")
    private boolean purchased;
    
    private String category; // produce, dairy, meat, etc.
    
    private String notes;
} 