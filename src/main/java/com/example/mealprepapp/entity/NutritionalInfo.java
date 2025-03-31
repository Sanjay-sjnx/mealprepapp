package com.example.mealprepapp.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NutritionalInfo {
    private String nutrientName; // e.g., Vitamin A, Iron, Calcium
    private Double amount;
    private String unit; // e.g., mg, g, IU
    private Double dailyValuePercentage; // % of recommended daily intake
} 