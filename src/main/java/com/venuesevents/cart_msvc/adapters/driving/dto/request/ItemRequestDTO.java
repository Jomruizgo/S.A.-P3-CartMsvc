package com.venuesevents.cart_msvc.adapters.driving.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemRequestDTO {
    private String ticketId;
    private int quantity;
}
