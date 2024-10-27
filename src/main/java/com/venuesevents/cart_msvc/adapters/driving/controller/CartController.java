package com.venuesevents.cart_msvc.adapters.driving.controller;

import com.venuesevents.cart_msvc.adapters.driving.dto.request.CartRequestDTO;
import com.venuesevents.cart_msvc.adapters.driving.dto.response.CartResponseDTO;
import com.venuesevents.cart_msvc.adapters.driving.mapper.request.CartRequestMapper;
import com.venuesevents.cart_msvc.adapters.driving.mapper.response.CartResponseMapper;
import com.venuesevents.cart_msvc.domain.api.ICartService;
import com.venuesevents.cart_msvc.domain.model.Cart;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final ICartService cartService;

    public CartController(ICartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<CartResponseDTO> createOrUpdateCart(@RequestBody CartRequestDTO cartRequestDTO) {
        Cart cart = CartRequestMapper.toDomain(cartRequestDTO);

        CartResponseDTO cartResponseDTO = CartResponseMapper.toResponse(
                cartService.createOrUpdateCart(cart));

        return new ResponseEntity<>(cartResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartResponseDTO> getCart(@PathVariable Long id) {
        Cart cart = cartService.getCart(id);
        CartResponseDTO cartResponseDTO = CartResponseMapper.toResponse(cart);
        return new ResponseEntity<>(cartResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
