package com.example.ecommerceproject.services;

import com.example.ecommerceproject.exceptions.ProductServiceException;
import com.example.ecommerceproject.models.Category;
import com.example.ecommerceproject.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(long id) throws ProductServiceException;
    Product addProduct(String title, Double price, String description,
                       String imageUrl, String category) throws ProductServiceException;
    Product deleteProduct(long id) throws ProductServiceException;
    Product updateProduct(Long id, String title, Double price, String description,
                          String imageUrl, String category) throws ProductServiceException;
    Page<Product> getAllProducts(int pageNumber, int pageSize, String fieldName) throws ProductServiceException;
}

