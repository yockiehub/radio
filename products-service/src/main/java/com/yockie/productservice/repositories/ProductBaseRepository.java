package com.yockie.productservice.repositories;

import com.yockie.productservice.models.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductBaseRepository<T extends Product> extends CrudRepository<Product, String> {

    List<Product> findByName(String name);
    Product findById(Long id);
    List<Product> findAll();

}
