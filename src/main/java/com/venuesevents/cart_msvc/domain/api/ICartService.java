package com.venuesevents.cart_msvc.domain.api;

import com.venuesevents.cart_msvc.domain.model.Cart;
import com.venuesevents.cart_msvc.domain.model.Item;

public interface ICartService {

    Cart createOrUpdateCart(Cart cart);

    Cart getCart(String cartId);

    Cart getCartByUserId(Long userId);

    Cart addItemToCart(Long userId, Item item);

    Cart removeItemFromCart(Long userId, String itemId);

    void deleteCart(String cartId);

    String purchaseCart(String cartId);

}
