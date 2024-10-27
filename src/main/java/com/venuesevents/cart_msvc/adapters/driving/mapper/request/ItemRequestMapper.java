package com.venuesevents.cart_msvc.adapters.driving.mapper.request;

import com.venuesevents.cart_msvc.adapters.driving.dto.request.ItemRequestDTO;
import com.venuesevents.cart_msvc.domain.model.Item;

public class ItemRequestMapper {

    public static Item toDomain(ItemRequestDTO itemRequestDTO) {
        Item item = new Item();
        item.setTicketId(itemRequestDTO.getTicketId());
        item.setSelectedQuantity(itemRequestDTO.getQuantity());
        return item;
    }
}
