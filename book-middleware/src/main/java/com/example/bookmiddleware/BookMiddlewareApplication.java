package com.example.bookmiddleware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BookMiddlewareApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookMiddlewareApplication.class, args);
    }
}
