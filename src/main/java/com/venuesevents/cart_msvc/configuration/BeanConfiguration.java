package com.venuesevents.cart_msvc.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.venuesevents.cart_msvc.adapters.driven.data.feing.adapter.EventAdapter;
import com.venuesevents.cart_msvc.adapters.driven.data.feing.client.IEventFeignClient;
import com.venuesevents.cart_msvc.adapters.driven.data.feing.mapper.ITicketMapper;
import com.venuesevents.cart_msvc.adapters.driven.data.redis.RedisCartAdapter;
import com.venuesevents.cart_msvc.domain.api.ICartService;
import com.venuesevents.cart_msvc.domain.api.usecase.CartUseCase;
import com.venuesevents.cart_msvc.domain.spi.ICartPersistencePort;
import com.venuesevents.cart_msvc.domain.spi.IEventServicePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class BeanConfiguration {
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;
    private final IEventFeignClient eventFeignClient;
    private final ITicketMapper ticketMapper;

    public BeanConfiguration(RedisTemplate<String, Object> redisTemplate, ObjectMapper objectMapper, IEventFeignClient eventFeignClient, ITicketMapper ticketMapper) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
        this.eventFeignClient = eventFeignClient;
        this.ticketMapper = ticketMapper;
    }

    //Service Ports
    @Bean
    public ICartService cartService(){ return new CartUseCase(cartPersistencePort(), eventServicePort()); }

    @Bean
    public ICartPersistencePort cartPersistencePort(){ return new RedisCartAdapter(redisTemplate, objectMapper);}

    @Bean
    public IEventServicePort eventServicePort(){ return new EventAdapter(eventFeignClient, ticketMapper); }


}
