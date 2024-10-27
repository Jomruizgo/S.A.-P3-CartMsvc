package com.venuesevents.cart_msvc.domain.api.usecase;

import com.venuesevents.cart_msvc.domain.api.ICartService;
import com.venuesevents.cart_msvc.domain.model.Cart;
import com.venuesevents.cart_msvc.domain.spi.ICartPersistencePort;
import com.venuesevents.cart_msvc.domain.spi.IEventServicePort;

import java.util.ArrayList;
import java.util.List;

public class CartUseCase implements ICartService {

    private final ICartPersistencePort cartPersistencePort;
    private final IEventServicePort eventServicePort;

    public CartUseCase(ICartPersistencePort cartPersistencePort, IEventServicePort eventServicePort) {
        this.cartPersistencePort = cartPersistencePort;
        this.eventServicePort = eventServicePort;
    }

    @Override
    public Cart createOrUpdateCart(Cart cart) {
        List<String> ticketsId = new ArrayList<>();

        cart.getItems().forEach(item -> ticketsId.add(item.getTicketId()));

        if (ticketsId.isEmpty()){
            throw new IllegalStateException("Cart should have completed items");
        }

        cart.setItems(eventServicePort.getTickets(ticketsId));

        return cartPersistencePort.saveCart(cart);
    }

    @Override
    public Cart getCart(Long cartId) {
        return cartPersistencePort.getCart(cartId);
    }

    @Override
    public void deleteCart(Long cartId) {
        cartPersistencePort.deleteCart(cartId);
    }
}
