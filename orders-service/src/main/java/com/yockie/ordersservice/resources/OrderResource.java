package com.yockie.ordersservice.resources;

import com.yockie.ordersservice.models.MyOrder;
import com.yockie.ordersservice.repositories.MyOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:8100"},
        allowedHeaders = {"Authorization", "Cache-Control", "Content-Type","Access-Control-Allow-Origin"},
        methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
@RequestMapping("orders")
public class OrderResource {

    @Autowired
    MyOrderRepository myOrderRepository;

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/test")
    public String addingOrdersTest() {
        HashMap<Long, Integer> prods = new HashMap<>();
        prods.put(1L, 2);
        prods.put(2L, 3);
        MyOrder myOrder = new MyOrder("Available", new Date().toString(), new Date().toString(), "Miau SA", prods);
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

    @RequestMapping("/addorder")
    @PostMapping
    public MyOrder addMyOrder(@RequestBody MyOrder order) {

        System.out.println(order.getId());
        for (Map.Entry<Long, Integer> prod: order.getProds().entrySet()) {
            // When running locally
            // restTemplate.put("http://localhost:8080/products/reduceamount/" + String.valueOf(prod.getKey()), prod.getValue());
            // When running on docker container
            restTemplate.put("http://172.17.0.4:8080/products/reduceamount/" + String.valueOf(prod.getKey()), prod.getValue());
        }
        myOrderRepository.save(order);

        return order;
    };

    @RequestMapping("/editorderstatus")
    @PutMapping
    public MyOrder editOrder(@RequestBody MyOrder order){
        MyOrder o = myOrderRepository.findById(order.getId());
        o.setStatus(order.getStatus());
        myOrderRepository.save(o);
        return order;
    };


    @RequestMapping("/delete/{orderId}")
    @DeleteMapping
    public String deleteOrder(@PathVariable("orderId") Long id) {

        myOrderRepository.delete(myOrderRepository.findById(id));
        return "Order deleted";
    }
}
