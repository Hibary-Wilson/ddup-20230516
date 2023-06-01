package com.commodity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient // 向eureka注册中心注册自己的信息
@SpringBootApplication
public class CommodityApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommodityApplication.class, args);
    }

}
