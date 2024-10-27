package com.venuesevents.cart_msvc.adapters.driven.data.feing.client;

import com.venuesevents.cart_msvc.adapters.driven.data.feing.dtoIn.TicketResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "${gateway.service.name}")
public interface IEventFeignClient {
    @GetMapping("/api/event/ticket/batch")
    List<TicketResponseDto> getTicketsByIds(@RequestParam("ticketIds") List<String> ticketIds);
}
