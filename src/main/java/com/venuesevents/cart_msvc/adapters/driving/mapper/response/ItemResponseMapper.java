package com.venuesevents.cart_msvc.adapters.driving.mapper.response;

import com.venuesevents.cart_msvc.adapters.driving.dto.response.ItemResponseDTO;
import com.venuesevents.cart_msvc.domain.model.Item;

public class ItemResponseMapper {

    public static ItemResponseDTO toResponse(Item item) {
        ItemResponseDTO itemResponseDTO = new ItemResponseDTO();
        itemResponseDTO.setId(item.getId());
        itemResponseDTO.setTicketId(item.getTicketId());
        itemResponseDTO.setName(item.getName());
        itemResponseDTO.setQuantity(item.getSelectedQuantity());
        itemResponseDTO.setPrice(item.getPrice());
        itemResponseDTO.setAddedAt(item.getAddedAt());
        return itemResponseDTO;
    }
}
