package com.example.ecommerceproject.services;

import com.example.ecommerceproject.exceptions.ProductServiceException;
import com.example.ecommerceproject.models.Category;
import com.example.ecommerceproject.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(long id) throws ProductServiceException;
    Product addProduct(String title, Double price, String description,
                       String imageUrl, Category category) throws ProductServiceException;
    void deleteProduct(long id) throws ProductServiceException;
    Product updateProduct(Long id, String title, Double price, String description,
                          String imageUrl, Category category) throws ProductServiceException;
    List<Product> getAllProducts() throws ProductServiceException;
}

