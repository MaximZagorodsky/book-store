package com.example.bookcore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BookCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookCoreApplication.class, args);
    }

}
