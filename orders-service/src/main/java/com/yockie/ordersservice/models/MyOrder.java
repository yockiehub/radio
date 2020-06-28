package com.yockie.ordersservice.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.HashMap;

@Entity
public class MyOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String status;
    private String creationDate;
    private String deliveryDate;
    private String customer;
    private HashMap<Long, Integer> prods = new HashMap<>();

    protected MyOrder() {}

    public MyOrder(String status, String creationDate, String deliveryDate, String customer, HashMap<Long, Integer> prods) {
        this.status = status;
        this.creationDate = creationDate;
        this.deliveryDate = deliveryDate;
        this.customer = customer;
        this.prods = prods;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public HashMap<Long, Integer> getProds() {
        return prods;
    }

    public void setProds(HashMap<Long, Integer> prods) {
        this.prods = prods;
    }
}
