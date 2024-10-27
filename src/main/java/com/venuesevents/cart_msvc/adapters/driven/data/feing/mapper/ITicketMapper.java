package com.venuesevents.cart_msvc.adapters.driven.data.feing.mapper;

import com.venuesevents.cart_msvc.adapters.driven.data.feing.dtoIn.TicketResponseDto;
import com.venuesevents.cart_msvc.domain.model.Item;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ITicketMapper {
    Item toModel(TicketResponseDto ticketResponseDto);

    List<Item> toModelList(List<TicketResponseDto> tickets);
}
