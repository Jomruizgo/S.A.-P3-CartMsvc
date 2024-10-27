package com.venuesevents.cart_msvc.adapters.driven.data.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.venuesevents.cart_msvc.domain.model.Cart;
import com.venuesevents.cart_msvc.domain.model.Item;
import com.venuesevents.cart_msvc.domain.spi.ICartPersistencePort;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.UUID;

public class RedisCartAdapter implements ICartPersistencePort {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;

    public RedisCartAdapter(RedisTemplate<String, Object> redisTemplate, ObjectMapper objectMapper) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public Cart saveCart(Cart cart) {
        // Generar un ID para el cart si no está asignado
        if (cart.getId() == null) {
            cart.setId(generateId());
        }

        // Asignar ID a cada Item si no lo tienen
        if (cart.getItems() != null) {
            cart.getItems().forEach(item -> {
                if (item.getId() == null) {
                    item.setId(generateId());
                }
            });
        }

        // Asocia userId con cartId para consulta rápida
        redisTemplate.opsForValue().set("user_cart:" + cart.getUserId(), cart.getId());
        redisTemplate.opsForValue().set("cart:" + cart.getId(), cart);
        return cart;
    }

    @Override
    public Cart getCart(String cartId) {
        Object redisCart = redisTemplate.opsForValue().get("cart:" + cartId);
        if (redisCart != null) {
            return objectMapper.convertValue(redisCart, Cart.class);
        }
        return null;
    }

    @Override
    public Cart getCartByUserId(Long userId) {
        String cartId = (String) redisTemplate.opsForValue().get("user_cart:" + userId);
        return cartId != null ? getCart(cartId) : null;
    }

    @Override
    public void deleteCart(String cartId) {
        Cart cart = getCart(cartId);
        if (cart != null) {
            redisTemplate.delete("cart:" + cartId);
            redisTemplate.delete("user_cart:" + cart.getUserId());
        }
    }

    @Override
    public Cart addItemToCart(Long userId, Item item) {
        Cart cart = getCartByUserId(userId);
        if (cart == null) throw new IllegalArgumentException("Cart not found for user");

        item.setId(generateId());
        cart.getItems().add(item);
        return saveCart(cart);
    }

    @Override
    public Cart removeItemFromCart(Long userId, String itemId) {
        Cart cart = getCartByUserId(userId);
        if (cart == null) throw new IllegalArgumentException("Cart not found for user");

        cart.getItems().removeIf(item -> item.getId().equals(itemId));
        return saveCart(cart);
    }

    private String generateId() {
        return UUID.randomUUID().toString(); // Genera un número positivo
    }
}