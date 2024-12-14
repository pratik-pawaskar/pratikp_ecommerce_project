package com.example.ecommerceproject.services;

import com.example.ecommerceproject.dto.FakeStoreProductDTO;
import com.example.ecommerceproject.exceptions.ProductServiceException;
import com.example.ecommerceproject.models.Category;
import com.example.ecommerceproject.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Service
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Product getSingleProduct(long id) throws ProductServiceException {
        FakeStoreProductDTO fspd = restTemplate.getForObject(
                "https://fakestoreapi.com/products/"+id,
                FakeStoreProductDTO.class);
        if (fspd == null){
            throw new ProductServiceException("Product not found for id: "+id);
        }
        System.out.println(fspd.toString());
        return fspd.getProduct();
    }

    public Product addProduct(String title, Double price, String description,
                              String imageUrl, Category category) throws ProductServiceException{
        FakeStoreProductDTO fsdto = new FakeStoreProductDTO();
        fsdto.setTitle(title);
        fsdto.setPrice(price);
        fsdto.setDescription(description);
        fsdto.setImage(imageUrl);
        fsdto.setCategory(category.getTitle());
        FakeStoreProductDTO fspd = restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                fsdto,
                FakeStoreProductDTO.class);
        if (fspd == null){
            throw new ProductServiceException("Unable to add the product");
        }
        return fspd.getProduct();
    }

    public Product deleteProduct(long id) throws ProductServiceException{
        FakeStoreProductDTO fspd = restTemplate.getForObject(
                "https://fakestoreapi.com/products/"+id,
                FakeStoreProductDTO.class);
        if (fspd == null){
            throw new ProductServiceException("Unable to delete the product");
        }
        return fspd.getProduct();
    }

    public Product updateProduct(Long id, String title, Double price, String description,
                                 String imageUrl, Category category) throws ProductServiceException{
        FakeStoreProductDTO fsdto = new FakeStoreProductDTO();
        fsdto.setTitle(title);
        fsdto.setPrice(price);
        fsdto.setDescription(description);
        fsdto.setImage(imageUrl);
        fsdto.setCategory(category.getTitle());
        FakeStoreProductDTO fspd = restTemplate.postForObject(
                "https://fakestoreapi.com/products/"+id,
                fsdto,
                FakeStoreProductDTO.class);
        if (fspd == null){
            throw new ProductServiceException("Unable to update the product");
        }
        return fspd.getProduct();
    }
}
