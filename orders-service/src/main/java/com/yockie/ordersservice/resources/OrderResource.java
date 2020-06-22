package com.yockie.ordersservice.resources;

import com.yockie.ordersservice.models.Order;
import com.yockie.ordersservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("orders")
public class OrderResource {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/{orderId}")
    public List<Order> getOrders(@PathVariable("orderId") String id) {


        List<Order> orders = Arrays.asList(new Order(id, "Delivered", new Date().toString(), new Date().toString(), Arrays.asList("foo")));

        return orders.stream().map(order -> {
            order.getProductIdList().stream().map(productId -> {
                Product product = restTemplate.getForObject("http://localhost:8080/products/" + productId, Product.class);
                return product;
            }).collect(Collectors.toList());

            return new Order(id, "Delivered", new Date().toString(), new Date().toString(), Arrays.asList("p1"));
        }).collect(Collectors.toList());
    }
}
