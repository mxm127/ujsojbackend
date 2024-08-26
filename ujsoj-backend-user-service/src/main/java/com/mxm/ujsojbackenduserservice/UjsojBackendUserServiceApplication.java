package com.mxm.ujsojbackenduserservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.mxm.ujsojbackenduserservice.mapper")
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@ComponentScan("com.mxm")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.mxm.ujsojbackendserviceclient.service"})
public class UjsojBackendUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UjsojBackendUserServiceApplication.class, args);
    }

}
