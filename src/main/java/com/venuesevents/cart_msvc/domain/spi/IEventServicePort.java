package com.venuesevents.cart_msvc.domain.spi;

import com.venuesevents.cart_msvc.domain.model.Item;

import java.util.List;
import java.util.Map;

public interface IEventServicePort {
    List<Item> getTickets(List<String> ticketsId);

    Map<String, Integer> verifyAndBlockTicketQuantities(Map<String, Integer> ticketQuantities);
}