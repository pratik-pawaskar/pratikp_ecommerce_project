package com.example.ecommerceproject.services;

import com.example.ecommerceproject.exceptions.CategoryServiceException;
import com.example.ecommerceproject.models.Category;
import com.example.ecommerceproject.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService_MyDB implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService_MyDB(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category createCategory(String category_title) throws CategoryServiceException {
        Category category = new Category(category_title);
        DateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
        String formattedDate1 = dateFormat1.format(new Date());
        category.setCreatedOn(formattedDate1);
        category.setUpdatedOn(formattedDate1);
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long id, String category_title) throws CategoryServiceException {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            Category category1 = category.get();
            category1.setTitle(category_title);
            DateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
            String formattedDate1 = dateFormat1.format(new Date());
            category1.setUpdatedOn(formattedDate1);
            return categoryRepository.save(category1);
        }
        else {
            throw new CategoryServiceException("Category with id:" + id + " not found");
        }
    }

    @Override
    public Category deleteCategory(Long id) throws CategoryServiceException {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            Category category1 = category.get();
            category1.setDeleted(true);
            return categoryRepository.save(category1);
        }
        else {
            throw new CategoryServiceException("Category with id:" + id + " not found");
        }
    }

    @Override
    public Category getCategoryById(Long id) throws CategoryServiceException{
        Optional<Category> cat = categoryRepository.findById(id);
        if (cat.isPresent()) {
            return cat.get();
        }
        else {
            throw new CategoryServiceException("Category with id:" + id + " not found");
        }
    }

    @Override
    public List<Category> getAllCategories() throws CategoryServiceException{
        List<Category> cat = categoryRepository.findByIsDeletedFalse();
        if (cat.isEmpty()) {
            throw new CategoryServiceException("No categories found");
        }
        else {
            return cat;
        }
    }
}
