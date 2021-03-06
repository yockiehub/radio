package com.yockie.productservice.models;

import javax.persistence.Entity;

@Entity
public class SingleProduct extends Product {

    private int amount;

    protected SingleProduct() {};

    public SingleProduct(String name, String description, int amount) {
        super(name, description);
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
