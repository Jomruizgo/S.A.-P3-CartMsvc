package com.venuesevents.cart_msvc.adapters.driven.data.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.venuesevents.cart_msvc.domain.model.Cart;
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

        // Guardar el carrito en Redis
        redisTemplate.opsForValue().set("cart:" + cart.getId(), cart);
        return cart;
    }

    @Override
    public Cart getCart(Long cartId) {
        Object redisCart = redisTemplate.opsForValue().get("cart:" + cartId);
        if (redisCart != null) {
            return objectMapper.convertValue(redisCart, Cart.class);
        }
        return null;
    }

    @Override
    public void deleteCart(Long cartId) {
        redisTemplate.delete("cart:" + cartId);
    }

    private Long generateId() {
        return UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE; // Genera un número positivo
    }
}