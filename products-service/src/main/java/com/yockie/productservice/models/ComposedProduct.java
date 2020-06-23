package com.yockie.productservice.models;

import javax.persistence.Entity;
import java.util.HashMap;

@Entity
public class ComposedProduct extends Product {

    private HashMap<Long, Integer> prods = new HashMap<>();

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
}
