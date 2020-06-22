package com.yockie.stockservice.repositories;

import com.yockie.stockservice.models.Stock;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StockRepository extends CrudRepository<Stock, String> {

    Stock findById(Long id);
    List<Stock> findAll();
}