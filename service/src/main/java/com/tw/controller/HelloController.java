package com.tw.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tanwei
 * @Desc
 * @date 2019年03月04日
 */

@RestController
public class HelloController {

    Logger logger= LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/hello")
    public String hello() {
        ServiceInstance instance = discoveryClient.getLocalServiceInstance();
        //打印服务的服务id
        logger.info("*********" + instance.getServiceId());
        return "hello,this is hello-service"+instance.getServiceId();
    }

}
