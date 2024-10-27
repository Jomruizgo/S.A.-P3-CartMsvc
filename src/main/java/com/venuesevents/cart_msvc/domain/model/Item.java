package com.venuesevents.cart_msvc.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Item {
    private String id;
    private String ticketId;
    private String cartId;
    private String name;
    private int availableQuantity;
    private int selectedQuantity;
    private LocalDateTime addedAt;
    private LocalDateTime updatedAt;
    private BigDecimal price;

    public Item(){}

    public Item(String id, String ticketId, String cartId, String name){
        this.id = id;
        this.ticketId = ticketId;
        this.cartId = cartId;
        this.name = name;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId; // Corregido
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity; // Corregido
    }

    public int getSelectedQuantity() {
        return selectedQuantity;
    }

    public void setSelectedQuantity(int selectedQuantity) {
        this.selectedQuantity = selectedQuantity;
    }

    public LocalDateTime getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
