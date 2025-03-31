package com.example.mealprepapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ingredients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String unit; // e.g., g, mL, tsp, tbsp
    private Double calories; // calories per unit
    private Double protein; // in grams per unit
    private Double carbs; // in grams per unit
    private Double fat; // in grams per unit
    
    @Column(name = "food_group")
    private String foodGroup; // e.g., dairy, meat, vegetables, fruits
    
    @ManyToMany(mappedBy = "ingredients")
    private Set<Recipe> recipes = new HashSet<>();
} 