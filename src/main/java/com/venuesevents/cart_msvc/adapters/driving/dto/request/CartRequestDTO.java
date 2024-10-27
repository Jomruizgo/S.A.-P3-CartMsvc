package com.venuesevents.cart_msvc.adapters.driving.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CartRequestDTO {
    private Long userId;
    private List<ItemRequestDTO> items;
}
