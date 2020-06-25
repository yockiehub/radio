package com.yockie.ordersservice.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
public class MyOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String status;
    private String creationDate;
    private String deliveryDate;
    private ArrayList<Integer> productIdList = new ArrayList<>();

    protected MyOrder() {}

    public MyOrder(String status, String creationDate, String deliveryDate, ArrayList<Integer> productIdList) {
        this.status = status;
        this.creationDate = creationDate;
        this.deliveryDate = deliveryDate;
        this.productIdList = productIdList;
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

    public ArrayList<Integer> getProductIdList() {
        return productIdList;
    }

    public void setProductIdList(ArrayList<Integer> productIdList) {
        this.productIdList = productIdList;
    }
}
