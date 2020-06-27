package com.yockie.productservice.models;

import com.sun.istack.Nullable;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.HashMap;

@Entity
public class ComposedProduct extends Product {

    private HashMap<Long, Integer> prods = new HashMap<>();

    @Transient
    @Nullable
    private int virtualAmount;

    protected ComposedProduct() {};

    public ComposedProduct(String name, String description, HashMap<Long, Integer> prods) {
        super(name, description);
        this.prods = prods;

    }

    public HashMap<Long, Integer> getProds() {
        return prods;
    }

    public void setProds(HashMap<Long, Integer> prods) {
        this.prods = prods;
    }

    public int getVirtualAmount() {
        return this.virtualAmount;
    }

    public void setVirtualAmount(int virtualAmount) {
        this.virtualAmount = virtualAmount;
    }
}
