package com.yockie.ordersservice.resources;

import com.yockie.ordersservice.models.MyOrder;
import com.yockie.ordersservice.repositories.MyOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderResource {

    @Autowired
    MyOrderRepository myOrderRepository;

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/test")
    public String addingOrdersTest() {
        ArrayList<Integer> al = new ArrayList<>();
        al.add(1);
        al.add(2);
        MyOrder myOrder = new MyOrder("Available", new Date().toString(), new Date().toString(), al);
        myOrderRepository.save(myOrder);
        return "MyOrder submitted";
    }

    @RequestMapping("/getall")
    @GetMapping
    public List<MyOrder> getAllOrders() {
        return myOrderRepository.findAll();
    }

    @RequestMapping("/getorder/{orderId}")
    @GetMapping
    public MyOrder getOrders(@PathVariable("orderId") Long id) {
        return myOrderRepository.findById(id);
    }
}

/*

List<MyOrder> orders = Arrays.asList(new MyOrder(id, "Delivered", new Date().toString(), new Date().toString(), Arrays.asList("foo")));

        return orders.stream().map(order -> {
            order.getProductIdList().stream().map(productId -> {
                Product product = restTemplate.getForObject("http://localhost:8080/products/" + productId, Product.class);
                return product;
            }).collect(Collectors.toList());

            return new MyOrder(id, "Delivered", new Date().toString(), new Date().toString(), Arrays.asList("p1"));
        }).collect(Collectors.toList());
 */
