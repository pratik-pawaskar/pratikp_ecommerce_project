package com.example.ecommerceproject.models;

import jakarta.persistence.Entity;

@Entity
public class Category extends BaseModel{
    private String title;

    public Category() {}

    public Category(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
