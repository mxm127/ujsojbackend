package com.mxm.ujsojbackendjudgeservice;

import com.mxm.ujsojbackendjudgeservice.rabbitmq.InitRabbitMq;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@ComponentScan("com.mxm")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.mxm.ujsojbackendserviceclient.service"})
public class UjsojBackendJudgeServiceApplication {

    public static void main(String[] args) {
//        初始化消息队列
        InitRabbitMq.doInit();
        SpringApplication.run(UjsojBackendJudgeServiceApplication.class, args);
    }

}
