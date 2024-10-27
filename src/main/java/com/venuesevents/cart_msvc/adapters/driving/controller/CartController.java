package com.venuesevents.cart_msvc.adapters.driving.controller;

import com.venuesevents.cart_msvc.adapters.driving.dto.request.CartRequestDTO;
import com.venuesevents.cart_msvc.adapters.driving.dto.response.CartResponseDTO;
import com.venuesevents.cart_msvc.adapters.driving.mapper.request.CartRequestMapper;
import com.venuesevents.cart_msvc.adapters.driving.mapper.response.CartResponseMapper;
import com.venuesevents.cart_msvc.domain.api.ICartService;
import com.venuesevents.cart_msvc.domain.model.Cart;
import com.venuesevents.cart_msvc.domain.model.Item;
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
    public ResponseEntity<CartResponseDTO> getCart(@PathVariable String id) {
        Cart cart = cartService.getCart(id);
        CartResponseDTO cartResponseDTO = CartResponseMapper.toResponse(cart);
        return new ResponseEntity<>(cartResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable String id) {
        cartService.deleteCart(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<CartResponseDTO> getCartByUserId(@PathVariable Long userId) {
        Cart cart = cartService.getCartByUserId(userId);
        CartResponseDTO cartResponseDTO = CartResponseMapper.toResponse(cart);
        return ResponseEntity.ok(cartResponseDTO);
    }

    @DeleteMapping("/user/{userId}/item/{itemId}")
    public ResponseEntity<CartResponseDTO> removeItemFromCart(@PathVariable Long userId, @PathVariable String itemId) {
        Cart updatedCart = cartService.removeItemFromCart(userId, itemId);
        CartResponseDTO cartResponseDTO = CartResponseMapper.toResponse(updatedCart);
        return ResponseEntity.ok(cartResponseDTO);
    }

    @PostMapping("/purchase/{cartId}")
    public ResponseEntity<String> purchaseCart(@PathVariable String cartId) {
        String response = cartService.purchaseCart(cartId);
        return ResponseEntity.ok(response);
    }
}
