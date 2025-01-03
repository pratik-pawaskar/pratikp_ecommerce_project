package com.example.ecommerceproject.controller;

import com.example.ecommerceproject.dto.ErrorDTO;
import com.example.ecommerceproject.exceptions.ProductServiceException;
import com.example.ecommerceproject.models.Category;
import com.example.ecommerceproject.models.Product;
import com.example.ecommerceproject.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;
    public ProductController(@Qualifier("ProductService_MyDB") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value="/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws ProductServiceException {
        Product pr = productService.getSingleProduct(id);
        ResponseEntity<Product> response = new ResponseEntity<>(pr, HttpStatus.OK);
        return response;
    }

    @PostMapping(value="/product/")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) throws ProductServiceException{
        Product pr = productService.addProduct(
                product.getTitle(), product.getPrice(), product.getDescription(),
                product.getImageUrl(), product.getCategory().getTitle());
        ResponseEntity<Product> response = new ResponseEntity<>(pr, HttpStatus.OK);
        return response;
    }

    @DeleteMapping(value="/product/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id) throws ProductServiceException{
        Product pr = productService.deleteProduct(id);
        ResponseEntity<Product> response = new ResponseEntity<>(pr, HttpStatus.OK);
        return response;
    }

    @PutMapping(value="/product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id,
                                                 @RequestBody Product product) throws ProductServiceException{
        Product pr = productService.updateProduct(
                id, product.getTitle(), product.getPrice(), product.getDescription(),
                product.getImageUrl(), product.getCategory().getTitle());
        ResponseEntity<Product> response = new ResponseEntity<>(pr, HttpStatus.OK);
        return response;
    }

    @GetMapping(value="/products")
    public ResponseEntity<List<Product>> getAllProducts() throws ProductServiceException{
        List<Product> product_list= productService.getAllProducts();
        ResponseEntity<List<Product>> response = new ResponseEntity<List<Product>>(product_list, HttpStatus.OK);
        return response;
    }

    @ExceptionHandler(ProductServiceException.class)
    public ResponseEntity<ErrorDTO> productServiceErrorHandler(Exception e){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setError(e.getMessage());
        ResponseEntity<ErrorDTO> response = new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
        return response;
    }
}
