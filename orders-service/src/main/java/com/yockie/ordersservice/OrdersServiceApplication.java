package com.yockie.ordersservice;

import com.yockie.ordersservice.repositories.MyOrderRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class OrdersServiceApplication {

	MyOrderRepository myOrderRepository;
	RestTemplate restTemplate = new RestTemplate();

	@Bean
	public MyOrderRepository getMyOrderRepository() {
		return this.myOrderRepository;
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return this.restTemplate;
	}

	public static void main(String[] args) {
		SpringApplication.run(OrdersServiceApplication.class, args);
	}

}
