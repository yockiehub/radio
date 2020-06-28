package com.yockie.productservice;

import com.yockie.productservice.repositories.ProductBaseRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ProductServiceApplication {

	ProductBaseRepository productBaseRepository;
	RestTemplate restTemplate = new RestTemplate();

	@Bean
	public ProductBaseRepository getProductBaseRepository() {
		return productBaseRepository;
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return this.restTemplate;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

}
