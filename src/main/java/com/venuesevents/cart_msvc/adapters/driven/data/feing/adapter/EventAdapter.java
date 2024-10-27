package com.venuesevents.cart_msvc.adapters.driven.data.feing.adapter;

import com.venuesevents.cart_msvc.adapters.driven.data.feing.client.IEventFeignClient;
import com.venuesevents.cart_msvc.adapters.driven.data.feing.exceptions.ResourceNotFoundException;
import com.venuesevents.cart_msvc.adapters.driven.data.feing.exceptions.ServiceUnavailableException;
import com.venuesevents.cart_msvc.adapters.driven.data.feing.exceptions.TooManyRequestsException;
import com.venuesevents.cart_msvc.adapters.driven.data.feing.mapper.ITicketMapper;
import com.venuesevents.cart_msvc.adapters.driven.data.feing.util.FeignConstants;
import com.venuesevents.cart_msvc.domain.model.Item;
import com.venuesevents.cart_msvc.domain.spi.IEventServicePort;
import feign.FeignException;
import feign.RetryableException;

import java.util.List;

public class EventAdapter implements IEventServicePort {
    private final IEventFeignClient eventFeignClient;
    private final ITicketMapper ticketMapper;

    public EventAdapter(IEventFeignClient eventFeignClient, ITicketMapper ticketMapper) {
        this.eventFeignClient = eventFeignClient;
        this.ticketMapper = ticketMapper;
    }


    @Override
    public List<Item> getTickets(List<String> ticketsId) {
        if (ticketsId.isEmpty()){
            throw new IllegalArgumentException("Ticket list must not be empty");

        }
        try {
            return ticketMapper.toModelList(eventFeignClient.getTicketsByIds(ticketsId));

        }catch (RetryableException e){
            throw new ServiceUnavailableException(FeignConstants.EVENT_SERVICE_UNAVAILABLE_MESSAGE);

        } catch (FeignException.NotFound e) {
            throw new ResourceNotFoundException(FeignConstants.EVENT_SERVICE_NOT_FOUND_MESSAGE);

        } catch (FeignException e) {
            if (e.status() == 429) {
                throw new TooManyRequestsException(FeignConstants.TOO_MANY_REQUEST_EVENT_SERVICE_MESSAGE);
            } else {
                throw new ServiceUnavailableException(FeignConstants.EVENT_COMMUNICATION_FAILED_MESSAGE);
            }
        }
    }
}
