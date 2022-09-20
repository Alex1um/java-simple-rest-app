package com.example.restdemo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository<T extends Product> extends JpaRepository<T, Long> {
}