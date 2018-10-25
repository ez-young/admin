package com.hzhetun.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author 陈炳坤
 * @date 2018/01/03
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableEurekaClient
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }
}
