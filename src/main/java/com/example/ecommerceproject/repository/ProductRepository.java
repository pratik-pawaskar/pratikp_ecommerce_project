package com.example.ecommerceproject.repository;

import com.example.ecommerceproject.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
//    public List<Product> findByIsDeleted(boolean isDeleted);
    public Page<Product> findByIsDeleted(boolean isDeleted, Pageable pageable);
}
