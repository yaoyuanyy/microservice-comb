package com.skyler.cobweb;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.kafka.annotation.EnableKafka;


@EnableEurekaClient
@SpringBootApplication
@EnableKafka
@MapperScan("com.skyler.cobweb.mybatis.mapper.**")
public class CombServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CombServerApplication.class, args);
    }
}
