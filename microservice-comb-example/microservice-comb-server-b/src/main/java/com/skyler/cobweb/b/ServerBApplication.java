package com.skyler.cobweb.b;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

//
@EnableFeignClients(basePackages = {"com.skyler"})
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = {"com.skyler"})
public class ServerBApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerBApplication.class, args);
    }
}
