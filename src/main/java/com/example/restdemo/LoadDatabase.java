package com.example.restdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ProductRepository repository) {
        return args -> {
            log.info("loaded");
            log.info("added new entity: " + repository.save(new Laptop("13221", "Acer", 100., 10, 17)));
//            log.info("added new entity: " + repository.save(new Laptop("13221", "Acer", 100., 10, 18)));
            log.info("added new entity: " + repository.save(new HardDrive("2901", "WD", 249.99, 50, 4096)));
            log.info("added new entity: " + repository.save(new PC("2313", "Asus", 999.99, 1, PCFormFactor.Desktop)));
            log.info("added new entity: " + repository.save(new PC("11111", "LG", 249.99, 50, PCFormFactor.Monoblock)));
            log.info("added new entity: " + repository.save(new PC("39991", "Asus", 99.99, 100, PCFormFactor.Nettop)));
            log.info("added new entity: " + repository.save(new Monitor("23.1", "LG", 1249.99, 2, 24)));
        };
    }
}
