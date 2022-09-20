package com.example.restdemo;

import javax.persistence.Entity;

@Entity
public class HardDrive extends Product {
    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    private Integer capacity;

    public HardDrive(String series_number, String manufacturer, Double price, Integer stock_count, Integer capacity) {
        super(series_number, manufacturer, price, stock_count);
        this.capacity = capacity;
    }

    public HardDrive() {

    }
}
