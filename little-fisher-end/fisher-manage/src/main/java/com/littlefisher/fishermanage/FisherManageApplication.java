package com.littlefisher.fishermanage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@ComponentScan(basePackages = {"com.littlefisher.fishermanage", "com.littlefisher.base"})
public class FisherManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(FisherManageApplication.class, args);
    }

}
