package ru.beleychev.pianotask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:rest.properties")
public class PianotaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(PianotaskApplication.class, args);
    }
}

