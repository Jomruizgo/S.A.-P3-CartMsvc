package com.venuesevents.cart_msvc.domain.spi;

import com.venuesevents.cart_msvc.domain.model.Item;

import java.util.List;

public interface IEventServicePort {
    List<Item> getTickets(List<String> ticketsId);
}