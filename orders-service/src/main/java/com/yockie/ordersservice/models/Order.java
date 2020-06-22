package com.yockie.ordersservice.models;

import java.util.List;

public class Order {

    private String id;
    private String status;
    private String creationDate;
    private String deliveryDate;
    private List<String> productIdList;

    public Order(String id, String status, String creationDate, String deliveryDate, List<String> productIdList) {
        this.id = id;
        this.status = status;
        this.creationDate = creationDate;
        this.deliveryDate = deliveryDate;
        this.productIdList = productIdList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public List<String> getProductIdList() {
        return productIdList;
    }

    public void setProductIdList(List<String> productIdList) {
        this.productIdList = productIdList;
    }
}
