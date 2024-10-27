package com.venuesevents.cart_msvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CartMsvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartMsvcApplication.class, args);
	}

}
