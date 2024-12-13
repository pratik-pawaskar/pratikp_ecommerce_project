package com.example.ecommerceproject.models;

public class Category {
    private int id;
    private String title;

    public Category() {}

    public Category(int id, String name) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
