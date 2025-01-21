package com.example.ecommerceproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Category extends BaseModel{
    private String title;

    @JsonIgnore
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> products;

    public Category() {}

    public Category(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
