package com.venuesevents.cart_msvc.domain.api;

import com.venuesevents.cart_msvc.domain.model.Cart;

public interface ICartService {

    Cart createOrUpdateCart(Cart cart);

    Cart getCart(Long cartId);

    void deleteCart(Long cartId);
}
