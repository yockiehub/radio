package com.yockie.productservice.models;

import javax.persistence.Entity;

@Entity
public class SingleProduct extends Product {

    private int amount;

    public SingleProduct(String name, String description, int amount) {
        super(name, description);
        this.amount = amount;
    }

    protected SingleProduct() {};

    public int getAmount() {
        return amount;
    }

    public void setStockId(int amount) {
        this.amount = amount;
    }
}
