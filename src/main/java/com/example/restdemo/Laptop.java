package com.example.restdemo;

import org.hibernate.procedure.ParameterMisuseException;

import javax.persistence.Entity;
import java.util.Set;


@Entity
public class Laptop extends Product {
    private static final Set<Integer> LaptopSizes = Set.of(13, 14, 15, 17);
    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        if (LaptopSizes.contains(size)) {
            this.size = size;
        }
    }

    private Integer size;

    public Laptop(String series_number, String manufacturer, Double price, Integer stock_count, Integer size) {
        super(series_number, manufacturer, price, stock_count);
        if (!LaptopSizes.contains(size)) {
            throw new ParameterMisuseException("Laptop size can be only 13, 14, 15 or 17");
        }
        this.size = size;
    }

    public Laptop() {

    }
}
