package com.yockie.productservice.models;

import javax.persistence.Entity;

@Entity
public class SingleProduct extends Product {

    protected SingleProduct() {};

    public SingleProduct(String name, String description) {
        super(name, description);
    }
}
