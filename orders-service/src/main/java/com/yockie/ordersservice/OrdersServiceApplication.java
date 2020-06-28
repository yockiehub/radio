package com.yockie.ordersservice;

import com.yockie.ordersservice.repositories.MyOrderRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OrdersServiceApplication {

	MyOrderRepository myOrderRepository;

	@Bean
	public MyOrderRepository getMyOrderRepository() {
		return this.myOrderRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(OrdersServiceApplication.class, args);
	}

}
