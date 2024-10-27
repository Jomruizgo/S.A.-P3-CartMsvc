package com.venuesevents.cart_msvc.domain.api.usecase;

import com.venuesevents.cart_msvc.domain.api.ICartService;
import com.venuesevents.cart_msvc.domain.exception.ObjectNotFoundException;
import com.venuesevents.cart_msvc.domain.model.Cart;
import com.venuesevents.cart_msvc.domain.model.Item;
import com.venuesevents.cart_msvc.domain.spi.ICartPersistencePort;
import com.venuesevents.cart_msvc.domain.spi.IEventServicePort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class CartUseCase implements ICartService {

    private final ICartPersistencePort cartPersistencePort;
    private final IEventServicePort eventServicePort;

    public CartUseCase(ICartPersistencePort cartPersistencePort, IEventServicePort eventServicePort) {
        this.cartPersistencePort = cartPersistencePort;
        this.eventServicePort = eventServicePort;
    }

    @Override
    public Cart createOrUpdateCart(Cart cart) {
        Cart existingCart = cartPersistencePort.getCartByUserId(cart.getUserId());
        if (existingCart != null) {
            cart.setId(existingCart.getId());
        }

        //Prepare information needed for make request to Event Service
        List<String> ticketsId = new ArrayList<>();

        cart.getItems().forEach(item -> ticketsId.add(item.getTicketId()));

        if (ticketsId.isEmpty()){
            throw new IllegalStateException("Cart should have completed items");
        }

        //Getting information from Event Service
        List<Item> itemsFromEventService = eventServicePort.getTickets(ticketsId);

        itemsFromEventService.forEach(item -> {
            //Setting quantity to items from event service
            for (int i = 0; i < cart.getItems().size(); i++){

                Item auxItem = cart.getItems().get(i);

                if(Objects.equals(auxItem.getTicketId(), item.getTicketId())){
                    item.setSelectedQuantity(auxItem.getSelectedQuantity());
                }
            }
            //Setting addedAt date.
            item.setAddedAt(LocalDateTime.now());
        });

        //Update cart with information from event service
        cart.setItems(itemsFromEventService);

        //Save and return cart
        return cartPersistencePort.saveCart(cart);
    }

    @Override
    public Cart getCart(String cartId) {
        Cart existingCart = cartPersistencePort.getCart(cartId);
        if(existingCart == null){
            throw new ObjectNotFoundException("Cart not found");
        }
        return existingCart;
    }

    @Override
    public Cart getCartByUserId(Long userId) {
        Cart existingCart = cartPersistencePort.getCartByUserId(userId);
        if(existingCart == null){
            throw new ObjectNotFoundException("Cart not found");
        }
        return existingCart;
    }

    @Override
    public Cart addItemToCart(Long userId, Item item) {
        return cartPersistencePort.addItemToCart(userId, item);
    }

    @Override
    public Cart removeItemFromCart(Long userId, String itemId) {
        return cartPersistencePort.removeItemFromCart(userId, itemId);
    }

    @Override
    public void deleteCart(String cartId) {
        cartPersistencePort.deleteCart(cartId);
    }


    @Override
    public String purchaseCart(String cartId) {
        Cart cart = cartPersistencePort.getCart(cartId);
        if (cart == null || cart.getItems().isEmpty()) {
            throw new IllegalArgumentException("Cart not found or is empty");
        }

        // Map with ticketId and quantity
        Map<String, Integer> ticketQuantities = cart.getItems().stream()
                .collect(Collectors.toMap(Item::getTicketId, Item::getSelectedQuantity));

        Map<String, Integer> insufficientStock = eventServicePort.verifyAndBlockTicketQuantities(ticketQuantities);

        if (!insufficientStock.isEmpty()) {
            return "Some items have updated availability: " + insufficientStock;
        }

        cartPersistencePort.deleteCart(cartId);
        return "Your order has been created. You have 20 minutes to complete the payment.";
    }
}
