package com.yockie.stockservice.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int amount;
    private Long simpleProductId;

    public Stock(int amount, Long simpleProductId) {
        this.amount = amount;
        this.simpleProductId = simpleProductId;
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

    public Long getSimpleProductId() {
        return simpleProductId;
    }

    public void setSimpleProductId(Long simpleProductId) {
        this.simpleProductId = simpleProductId;
    }
}
