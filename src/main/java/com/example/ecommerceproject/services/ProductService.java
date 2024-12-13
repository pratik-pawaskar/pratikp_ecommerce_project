package com.example.ecommerceproject.services;

import com.example.ecommerceproject.models.Category;
import com.example.ecommerceproject.models.Product;

public interface ProductService {
    Product getSingleProduct(long id);
    Product addProduct(String title, Double price, String description, String imageUrl, Category category);
    Product deleteProduct(long id);
    Product updateProduct(Long id, String title, Double price, String description, String imageUrl, Category category);
}

