package com.yockie.productservice;

import com.yockie.productservice.repositories.ProductBaseRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProductServiceApplication {

	ProductBaseRepository productBaseRepository;
	// StockRepository stockRepository;

	@Bean
	public ProductBaseRepository getProductBaseRepository() {
		return productBaseRepository;
	}

	/*@Bean
	public StockRepository getStockRepository() {
		return stockRepository;
	}*/

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

}
