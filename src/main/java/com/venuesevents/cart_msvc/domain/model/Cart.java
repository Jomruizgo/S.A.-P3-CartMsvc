package com.venuesevents.cart_msvc.domain.model;

import java.util.List;

public class Cart {
    private String id;
    private Long userId;
    private List<Item> items; // Lista de Items en el carrito

    public Cart(){}

    public Cart(String id, Long userId, List<Item> items) {
        this.id = id;
        this.userId = userId;
        this.items = items;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}

