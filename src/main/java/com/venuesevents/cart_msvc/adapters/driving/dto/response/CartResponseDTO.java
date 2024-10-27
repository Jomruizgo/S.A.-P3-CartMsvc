package com.venuesevents.cart_msvc.adapters.driving.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartResponseDTO {
    private String id;
    private Long userId;
    private List<ItemResponseDTO> items;
}
