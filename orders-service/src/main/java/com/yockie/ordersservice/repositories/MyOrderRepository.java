package com.yockie.ordersservice.repositories;

import com.yockie.ordersservice.models.MyOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MyOrderRepository extends CrudRepository<MyOrder, String> {

    MyOrder findById(Long id);
    List<MyOrder> findAll();

}
