package com.example.mealprepapp.repository;

import com.example.mealprepapp.entity.ShoppingList;
import com.example.mealprepapp.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {
    Page<ShoppingList> findByUser(User user, Pageable pageable);
} 