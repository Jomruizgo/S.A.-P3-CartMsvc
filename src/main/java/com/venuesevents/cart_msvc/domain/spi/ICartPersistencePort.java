package com.venuesevents.cart_msvc.domain.spi;

import com.venuesevents.cart_msvc.domain.model.Cart;
import com.venuesevents.cart_msvc.domain.model.Item;

import java.util.Map;

public interface ICartPersistencePort {
    Cart saveCart(Cart cart);
    Cart getCart(String cartId);
    Cart getCartByUserId(Long userId);
    void deleteCart(String cartId);
    Cart addItemToCart(Long userId, Item item);
    Cart removeItemFromCart(Long userId, String itemId);
}
