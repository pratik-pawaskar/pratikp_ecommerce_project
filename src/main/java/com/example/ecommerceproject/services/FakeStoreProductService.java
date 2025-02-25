package com.example.ecommerceproject.services;

import com.example.ecommerceproject.configurations.RedisTemplateConfig;
import com.example.ecommerceproject.dto.FakeStoreProductDTO;
import com.example.ecommerceproject.exceptions.ProductServiceException;
import com.example.ecommerceproject.models.Category;
import com.example.ecommerceproject.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;
    private RedisTemplate redisTemplate;

    public FakeStoreProductService(RestTemplate restTemplate, RedisTemplate redisTemplate) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }

    public Product getSingleProduct(long id) throws ProductServiceException {
        Product redisProduct = (Product) redisTemplate.opsForHash().get(
                "PRODUCTS", "PRODUCT"+id);

        if (redisProduct != null){
            System.out.println("Redis product found");
            return redisProduct;
        }
        else {
            System.out.println("Redis product not found");
            FakeStoreProductDTO fspd = restTemplate.getForObject(
                    "https://fakestoreapi.com/products/"+id,
                    FakeStoreProductDTO.class);
            if (fspd == null){
                throw new ProductServiceException("Product not found for id: "+id);
            }
            else {
                System.out.println("Stored product in redis");
                redisTemplate.opsForHash().put("PRODUCTS", "PRODUCT"+id, fspd.getProduct());
                return fspd.getProduct();
            }
        }
    }

    public Product addProduct(String title, Double price, String description,
                              String imageUrl, String category) throws ProductServiceException{
        FakeStoreProductDTO fsdto = new FakeStoreProductDTO();
        fsdto.setTitle(title);
        fsdto.setPrice(price);
        fsdto.setDescription(description);
        fsdto.setImage(imageUrl);
        fsdto.setCategory(category);
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
        System.out.println("Attempting to delete product with id: "+id);
        restTemplate.delete("https://fakestoreapi.com/products/"+id);
        return null;
    }

    public Product updateProduct(Long id, String title, Double price, String description,
                                 String imageUrl, String category) throws ProductServiceException{
        FakeStoreProductDTO fsdto = new FakeStoreProductDTO();
        fsdto.setTitle(title);
        fsdto.setPrice(price);
        fsdto.setDescription(description);
        fsdto.setImage(imageUrl);
        fsdto.setCategory(category);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<FakeStoreProductDTO> requestEntity = new HttpEntity<FakeStoreProductDTO>(fsdto, headers);

        FakeStoreProductDTO fspd = restTemplate.exchange(
                "https://fakestoreapi.com/products/"+id,
                HttpMethod.PUT, requestEntity, FakeStoreProductDTO.class, fsdto).getBody();
        if (fspd == null){
            throw new ProductServiceException("Unable to update the product");
        }
        return fspd.getProduct();
    }

//    public List<Product> getAllProducts() throws ProductServiceException{
//        FakeStoreProductDTO[] fspd_list = restTemplate.getForObject(
//                "https://fakestoreapi.com/products", FakeStoreProductDTO[].class
//        );
//        List<Product> product_list = new ArrayList<Product>();
//        for (FakeStoreProductDTO fspd: fspd_list){
//            product_list.add(fspd.getProduct());
//        }
//        System.out.println("Found all products inside getall: "+product_list);
//        return product_list;
//    }

    public Page<Product> getAllProducts(int pageNumber, int pageSize, String fieldName) throws ProductServiceException{
        return null;
    }
}
