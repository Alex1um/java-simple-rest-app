package com.example.restdemo;

import javax.persistence.Entity;

@Entity
public class Monitor extends Product {
    public Integer getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(Integer diagonal) {
        this.diagonal = diagonal;
    }

    private Integer diagonal;

    public Monitor(String series_number, String manufacturer, Double price, Integer stock_count, Integer diagonal) {
        super(series_number, manufacturer, price, stock_count);
        this.diagonal = diagonal;
    }

    public Monitor() {
    }
}
