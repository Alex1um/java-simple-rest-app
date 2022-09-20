package com.example.restdemo;

import javax.persistence.Entity;

@Entity
public class PC extends Product {

    public PCFormFactor getForm_factor() {
        return form_factor;
    }

    public void setForm_factor(PCFormFactor form_factor) {
        this.form_factor = form_factor;
    }

    private PCFormFactor form_factor;

    public PC(String series_number, String manufacturer, Double price, Integer stock_count, PCFormFactor form_factor) {
        super(series_number, manufacturer, price, stock_count);
        this.form_factor = form_factor;
    }

    public PC() {

    }

}
