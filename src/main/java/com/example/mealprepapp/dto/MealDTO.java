package com.example.mealprepapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealDTO {
    private Long id;
    private String name;
    private Integer calories;
    private String category;
    private LocalDateTime updatedAt;
} 