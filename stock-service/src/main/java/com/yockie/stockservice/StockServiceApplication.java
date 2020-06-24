package com.yockie.stockservice;

import com.yockie.stockservice.repositories.StockRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StockServiceApplication {

	StockRepository stockRepository;

	@Bean
	StockRepository getStockRepository() {
		return this.stockRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(StockServiceApplication.class, args);
	}

}
