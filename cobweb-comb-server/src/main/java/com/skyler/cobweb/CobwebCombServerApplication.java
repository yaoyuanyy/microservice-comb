package com.skyler.cobweb;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@SpringBootApplication
public class CobwebCombServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CobwebCombServerApplication.class, args);
    }
}
