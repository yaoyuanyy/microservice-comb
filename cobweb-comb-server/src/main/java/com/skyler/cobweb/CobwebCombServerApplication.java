package com.skyler.cobweb;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.kafka.annotation.EnableKafka;


@EnableEurekaClient
@SpringBootApplication
@EnableKafka
public class CobwebCombServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CobwebCombServerApplication.class, args);
    }
}
