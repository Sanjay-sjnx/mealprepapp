package com.example.mealprepapp.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MealStatistics {
    private String category;
    private Long count;
    private Double avgCalories;
    private Integer minCalories;
    private Integer maxCalories;
} 