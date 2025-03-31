package com.example.mealprepapp.controller;

import com.example.mealprepapp.entity.ShoppingList;
import com.example.mealprepapp.service.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/shopping-lists")
public class ShoppingListController {

    @Autowired
    private ShoppingListService shoppingListService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingList createShoppingList(@RequestBody ShoppingList shoppingList) {
        return shoppingListService.createShoppingList(shoppingList);
    }

    @PostMapping("/generate/{mealPlanId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingList generateShoppingList(@PathVariable Long mealPlanId) {
        return shoppingListService.generateShoppingListFromMealPlan(mealPlanId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingList> getShoppingListById(@PathVariable Long id) {
        Optional<ShoppingList> shoppingList = shoppingListService.getShoppingListById(id);
        return shoppingList.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public Page<ShoppingList> getShoppingListsByUser(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return shoppingListService.getShoppingListsByUser(userId, page, size);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShoppingList> updateShoppingList(@PathVariable Long id, @RequestBody ShoppingList shoppingList) {
        try {
            ShoppingList updatedShoppingList = shoppingListService.updateShoppingList(id, shoppingList);
            return ResponseEntity.ok(updatedShoppingList);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/mark-completed")
    public ResponseEntity<ShoppingList> markShoppingListAsCompleted(@PathVariable Long id) {
        try {
            ShoppingList updatedShoppingList = shoppingListService.markShoppingListAsCompleted(id);
            return ResponseEntity.ok(updatedShoppingList);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShoppingList(@PathVariable Long id) {
        try {
            shoppingListService.deleteShoppingList(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
} 