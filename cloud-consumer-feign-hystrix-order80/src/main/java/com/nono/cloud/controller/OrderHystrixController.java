package com.nono.cloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import com.nono.cloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping(value = "/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result = paymentHystrixService.paymentInfo_OK(id);
        return result;
    }
    @GetMapping(value = "/consumer/payment/hystrix/timeout/{id}")
    @HystrixCommand//(fallbackMethod = "paymentInfo_TimeOutFallbackMethod", commandProperties = {
            //正常业务等待时间 5000
            //@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
    //})
    public String paymentInfo_Timeout(@PathVariable("id") Integer id){
        int age = 10/0;
        String result = paymentHystrixService.paymentInfo_Timeout(id);
        return result;
    }
    public String paymentInfo_TimeOutFallbackMethod(@PathVariable("id") Integer id){
        return "80服务器繁忙";
    }
    public String payment_Global_FallbackMethod(){
        return "默认报错信息";
    }

}
