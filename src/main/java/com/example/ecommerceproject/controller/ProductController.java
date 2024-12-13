package com.example.ecommerceproject.controller;

import com.example.ecommerceproject.models.Category;
import com.example.ecommerceproject.models.Product;
import com.example.ecommerceproject.services.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    private ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value="/product/{id}")
    public Product getProductById(@PathVariable("id") Long id){
        Product pr = productService.getSingleProduct(id);
        return pr;
    }

    @PostMapping(value="/product/")
    public Product addProduct(@RequestBody Product product){
        Product pr = productService.addProduct(
                product.getTitle(), product.getPrice(), product.getDescription(),
                product.getImageUrl(), product.getCategory());
        return pr;
    }

    @RequestMapping(value="/hello/{username}", method=RequestMethod.GET)
    public String sayHello(@PathVariable("username") String name){
        return "Hello "+name+".How are you?";
    }
}
