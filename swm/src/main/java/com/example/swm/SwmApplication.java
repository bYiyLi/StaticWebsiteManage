package com.example.swm;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication(scanBasePackages = {"com.*"})
public class SwmApplication {
    public static void main(String[] args) {
        SpringApplication.run(SwmApplication.class, args);
    }
}
