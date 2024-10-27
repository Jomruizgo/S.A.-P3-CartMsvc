package com.venuesevents.cart_msvc.domain.spi;

import com.venuesevents.cart_msvc.domain.model.Cart;

public interface ICartPersistencePort {
    Cart saveCart(Cart cart);
    Cart getCart(Long cartId);
    void deleteCart(Long cartId);
}
