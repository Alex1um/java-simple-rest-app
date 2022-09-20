package com.example.restdemo;

import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class ProductController<T extends Product> {
    private final ProductRepository<T> repository;

    ProductController(ProductRepository<T> repository) {
        this.repository = repository;
    }

    @GetMapping("/products")
    List<T> getAll() {
        return repository.findAll();
    }

    @GetMapping("/products/{type}")
    List<T> allByType(@PathVariable String type) {
        if (Objects.equals(type, PC.class.getSimpleName())) {
            return repository.findAll((Example<T>) Example.of(new PC()));
        } else if (Objects.equals(type, Monitor.class.getSimpleName())) {
            return repository.findAll((Example<T>) Example.of(new Monitor()));
        } else if (Objects.equals(type, HardDrive.class.getSimpleName())) {
            return repository.findAll((Example<T>) Example.of(new HardDrive()));
        } else if (Objects.equals(type, Laptop.class.getSimpleName())) {
            return repository.findAll((Example<T>) Example.of(new Laptop()));
        } else {
            return List.of();
        }
    }

    @PostMapping("/product/new")
    T newProduct(@RequestBody T newProduct) {
        return repository.save(newProduct);
    }

    @GetMapping("/product/{id}")
    T getById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @DeleteMapping("/product/{id}")
    void deleteProduct(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PostMapping("/product/edit/{id}")
    T EditProduct(
            @PathVariable Long id,
            @RequestBody Map<String, String> body

    ) {
        return repository.findById(id)
                .map(product -> {
                    if (body.containsKey("series_number"))
                        product.setSeries_number(body.get("series_number"));
                    if (body.containsKey("manufacturer"))
                        product.setManufacturer(body.get("manufacturer"));
                    if (body.containsKey("price"))
                        product.setPrice(Double.parseDouble(body.get("price")));
                    if (body.containsKey("stock_count"))
                        product.setStock_count(Integer.parseInt(body.get("stock_count")));
                    if (product instanceof PC && body.containsKey("form_factor")) {
                        ((PC) product).setForm_factor(PCFormFactor.valueOf(body.get("form_factor")));
                    }
                    if (product instanceof HardDrive && body.containsKey("capacity")) {
                        ((HardDrive) product).setCapacity(Integer.parseInt(body.get("capacity")));
                    }
                    if (product instanceof Laptop && body.containsKey("size")) {
                        ((Laptop) product).setSize(Integer.parseInt(body.get("size")));
                    }
                    if (product instanceof Monitor && body.containsKey("diagonal")) {
                        ((Monitor) product).setDiagonal(Integer.parseInt(body.get("diagonal")));
                    }
                    return repository.save(product);
                })
                .orElseGet(() -> {
                    T rt = (T) new Product();
                    if (body.containsKey("form_factor")) {
                        PC product = new PC();
                        product.setForm_factor(PCFormFactor.valueOf(body.get("form_factor")));
                        rt = (T) product;
                    }
                    if (body.containsKey("capacity")) {
                        HardDrive product = new HardDrive();
                        product.setCapacity(Integer.parseInt(body.get("capacity")));
                        rt = (T) product;
                    }
                    if (body.containsKey("size")) {
                        Laptop product = new Laptop();
                        product.setSize(Integer.parseInt(body.get("size")));
                        rt = (T) product;
                    }
                    if (body.containsKey("diagonal")) {
                        Monitor product = new Monitor();
                        product.setDiagonal(Integer.parseInt(body.get("diagonal")));
                        rt = (T) product;
                    }
                    rt.setSeries_number(body.get("series_number"));
                    rt.setManufacturer(body.get("manufacturer"));
                    rt.setPrice(Double.parseDouble(body.get("price")));
                    rt.setStock_count(Integer.parseInt(body.get("stock_count")));
                    rt.setId(id);
                    return repository.save(rt);
                });
    }
}
