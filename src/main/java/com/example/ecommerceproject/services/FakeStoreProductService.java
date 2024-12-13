package com.example.ecommerceproject.services;

import com.example.ecommerceproject.dto.FakeStoreProductDTO;
import com.example.ecommerceproject.models.Category;
import com.example.ecommerceproject.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Product getSingleProduct(long id) {
        FakeStoreProductDTO fspd = restTemplate.getForObject(
                "https://fakestoreapi.com/products/"+id,
                FakeStoreProductDTO.class);
        System.out.println(fspd.toString());
        return fspd.getProduct();
    }

    public Product addProduct(String title, Double price, String description, String imageUrl, Category category) {
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
        return fspd.getProduct();
    }
}
