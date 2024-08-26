package com.mxm.ujsojbackendquestionservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.mxm.ujsojbackendquestionservice.mapper")
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@ComponentScan("com.mxm")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.mxm.ujsojbackendserviceclient.service"})
public class UjsojBackendQuestionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UjsojBackendQuestionServiceApplication.class, args);
    }

}
