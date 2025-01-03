package com.example.ecommerceproject.services;

import com.example.ecommerceproject.exceptions.CategoryServiceException;
import com.example.ecommerceproject.models.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(String category_title) throws CategoryServiceException;
    Category updateCategory(Long id, String category_title) throws CategoryServiceException;
    Category deleteCategory(Long id) throws CategoryServiceException;
    Category getCategoryById(Long id) throws CategoryServiceException;
    List<Category> getAllCategories() throws CategoryServiceException;
}
