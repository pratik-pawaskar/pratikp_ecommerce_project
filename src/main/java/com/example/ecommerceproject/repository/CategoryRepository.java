package com.example.ecommerceproject.repository;

import com.example.ecommerceproject.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    public Optional<Category> findByTitle(String title);
    public List<Category> findByIsDeletedFalse();
}
