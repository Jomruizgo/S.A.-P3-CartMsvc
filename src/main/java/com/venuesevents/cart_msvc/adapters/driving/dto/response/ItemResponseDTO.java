package com.venuesevents.cart_msvc.adapters.driving.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemResponseDTO {
    private String id;
    private String ticketId;
    private String name;
    private int quantity;
    private BigDecimal price;
    private LocalDateTime addedAt;
}
