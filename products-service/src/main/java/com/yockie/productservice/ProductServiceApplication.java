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

	/*@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final CorsConfiguration configuration = new CorsConfiguration();

		configuration.setAllowedOrigins(Arrays.asList(new String[]{"*"}));
		configuration.setAllowedMethods(Arrays.asList(new String[]{"GET", "POST", "PUT", "DELETE"}));
		configuration.setAllowCredentials(true);
		configuration.setAllowedHeaders(Arrays.asList(new String[]{"Authorization", "Cache-Control", "Content-Type"}));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}*/


	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

}
