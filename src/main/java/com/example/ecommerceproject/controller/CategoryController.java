package com.example.ecommerceproject.controller;

import com.example.ecommerceproject.dto.ErrorDTO;
import com.example.ecommerceproject.exceptions.CategoryServiceException;
import com.example.ecommerceproject.models.Category;
import com.example.ecommerceproject.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/category/")
    public ResponseEntity<Category> create_category(@RequestBody Category category)
            throws CategoryServiceException{
        Category ct = categoryService.createCategory(category.getTitle());
        ResponseEntity<Category> response = new ResponseEntity<>(ct, HttpStatus.OK);
        return response;
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<Category> update_category(@PathVariable Long id,
                                                    @RequestBody Category category)
            throws CategoryServiceException{
        Category ct = categoryService.updateCategory(id, category.getTitle());
        ResponseEntity<Category> response = new ResponseEntity<>(ct, HttpStatus.OK);
        return response;
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<Category> delete_category(@PathVariable Long id) throws CategoryServiceException{
        Category cat = categoryService.deleteCategory(id);
        ResponseEntity<Category> response = new ResponseEntity<>(cat, HttpStatus.OK);
        return response;
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Category> get_category(@PathVariable Long id) throws CategoryServiceException{
        Category cat = categoryService.getCategoryById(id);
        ResponseEntity<Category> response = new ResponseEntity<>(cat, HttpStatus.OK);
        return response;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> get_all_categories() throws CategoryServiceException{
        List<Category> cats = categoryService.getAllCategories();
        ResponseEntity<List<Category>> response = new ResponseEntity<>(cats, HttpStatus.OK);
        return response;
    }

    @ExceptionHandler(CategoryServiceException.class)
    public ResponseEntity<ErrorDTO> categoryServiceErrorHandler(Exception e){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setError(e.getMessage());
        ResponseEntity<ErrorDTO> response = new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
        return response;
    }
}

