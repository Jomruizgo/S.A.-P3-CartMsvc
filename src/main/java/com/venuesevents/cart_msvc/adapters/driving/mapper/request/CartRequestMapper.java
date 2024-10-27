package com.venuesevents.cart_msvc.adapters.driving.mapper.request;

import com.venuesevents.cart_msvc.adapters.driving.dto.request.CartRequestDTO;
import com.venuesevents.cart_msvc.domain.model.Cart;

import java.util.stream.Collectors;

public class CartRequestMapper {

    public static Cart toDomain(CartRequestDTO cartRequestDTO) {
        Cart cart = new Cart();
        cart.setUserId(cartRequestDTO.getUserId());
        cart.setItems(cartRequestDTO.getItems().stream()
                .map(ItemRequestMapper::toDomain)
                .collect(Collectors.toList()));
        return cart;
    }
}
