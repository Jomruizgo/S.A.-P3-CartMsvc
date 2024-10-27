package com.venuesevents.cart_msvc.adapters.driving.mapper.response;

import com.venuesevents.cart_msvc.adapters.driving.dto.response.CartResponseDTO;
import com.venuesevents.cart_msvc.domain.model.Cart;

import java.util.stream.Collectors;

public class CartResponseMapper {

    public static CartResponseDTO toResponse(Cart cart) {
        CartResponseDTO cartResponseDTO = new CartResponseDTO();
        cartResponseDTO.setId(cart.getId());
        cartResponseDTO.setUserId(cart.getUserId());
        cartResponseDTO.setItems(cart.getItems().stream()
                .map(ItemResponseMapper::toResponse)
                .collect(Collectors.toList()));
        return cartResponseDTO;
    }
}