package com.example.ecommerceproject.services;

import com.example.ecommerceproject.exceptions.ProductServiceException;
import com.example.ecommerceproject.models.Category;
import com.example.ecommerceproject.models.Product;
import com.example.ecommerceproject.repository.CategoryRepository;
import com.example.ecommerceproject.repository.ProductRepository;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("ProductService_MyDB")
public class ProductService_MyDB implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public ProductService_MyDB(
            ProductRepository productRepository,
            CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getSingleProduct(long id) throws ProductServiceException {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        }
        else {
            throw new ProductServiceException("Product not found for id:" + id);
        }
    }

    public Product addProduct(String title, Double price, String description,
                              String imageUrl, String category_title)
            throws ProductServiceException {
        Product product = new Product();
        Optional<Category> cat = categoryRepository.findByTitle(category_title);
        if(cat.isPresent()) {
            product.setCategory(cat.get());
        }
        else{
            Category category = new Category();
            category.setTitle(category_title);
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
            String formattedDate = dateFormat.format(new Date());
            category.setCreatedOn(formattedDate);
            category.setUpdatedOn(formattedDate);
            Category savedCategory = categoryRepository.save(category);
            product.setCategory(savedCategory);
        }
        product.setTitle(title);
        product.setPrice(price);
        product.setDescription(description);
        product.setImageUrl(imageUrl);
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
        String formattedDate = dateFormat.format(new Date());
        product.setCreatedOn(formattedDate);
        product.setUpdatedOn(formattedDate);
        return productRepository.save(product);
    }

    @Override
    public Product deleteProduct(long id) throws ProductServiceException {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            Product product1 = product.get();
            product1.setDeleted(true);
            return productRepository.save(product1);
        }
        else {
            throw new ProductServiceException("Product not found for id:" + id);
        }
    }

    @Override
    public Product updateProduct(Long id, String title, Double price,
                                 String description, String imageUrl,
                                 String category) throws ProductServiceException {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            Product fetchedProduct = product.get();
            fetchedProduct.setTitle(title);
            fetchedProduct.setPrice(price);
            fetchedProduct.setDescription(description);
            fetchedProduct.setImageUrl(imageUrl);
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
            String formattedDate = dateFormat.format(new Date());
            fetchedProduct.setUpdatedOn(formattedDate);
            Optional<Category> cat = categoryRepository.findByTitle(category);
            if (cat.isPresent()) {
                fetchedProduct.setCategory(cat.get());
            }
            else{
                Category category1 = new Category();
                category1.setTitle(category);
                DateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
                String formattedDate1 = dateFormat1.format(new Date());
                category1.setCreatedOn(formattedDate1);
                category1.setUpdatedOn(formattedDate1);
                Category savedCategory = categoryRepository.save(category1);
                fetchedProduct.setCategory(savedCategory);
            }
            return productRepository.save(fetchedProduct);
        }
        else {
            throw new ProductServiceException("Product not found for id:" + id);
        }
    }

//    @Override
//    public List<Product> getAllProducts() throws ProductServiceException {
//        List<Product> productsList = productRepository.findByIsDeleted(false);
//        if (productsList.isEmpty()){
//            throw new ProductServiceException("No products found");
//        }
//        return productsList;
//    }

    @Override
    public Page<Product> getAllProducts(int pageNumber, int pageSize, String fieldName) throws ProductServiceException {
        return productRepository.findByIsDeleted(
                false, PageRequest.of(pageNumber, pageSize, Sort.by(fieldName).ascending()));
    }
}
