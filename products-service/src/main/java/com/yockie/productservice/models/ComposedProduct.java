package com.yockie.productservice.models;

import javax.persistence.Entity;
import java.util.ArrayList;

@Entity
public class ComposedProduct extends Product {

    private ArrayList<Long> prodIds;

    protected ComposedProduct() {};

    public ComposedProduct(String name, String description, ArrayList<Long> prodIds) {
        super(name, description);
        this.prodIds = prodIds;
    }

    public ArrayList<Long> getProdIds() {
        return prodIds;
    }

    public void setStockIds(ArrayList<Long> prodIds) {
        this.prodIds = prodIds;
    }
}
