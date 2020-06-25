package com.yockie.productservice.models;

public class Stock {

    private Long id;
    private int amount;
    private Long singleProductId;

    public Stock(int amount, Long singleProductId) {
        this.amount = amount;
        this.singleProductId = singleProductId;
    }

    protected Stock() {};

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Long getSingleProductId() {
        return singleProductId;
    }

    public void setSingleProductId(Long singleProductId) {
        this.singleProductId = singleProductId;
    }

    @Override
    public String toString() {
        return "Id: " + this.id + ", Product Id: " + this.singleProductId + ", Amount: " + this.amount;
    }
}

