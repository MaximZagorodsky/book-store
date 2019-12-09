package com.example.bookdiscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class BookDiscoveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookDiscoveryApplication.class, args);
    }

}
