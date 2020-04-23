package com.nono.cloud.controller;

import com.nono.cloud.entities.CommonResult;
import com.nono.cloud.entities.Payment;
import com.nono.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;
    @Resource
    private DiscoveryClient discoveryClient;//获取服务信息
    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        //Log.info("插入结果--" + result);

        if(result > 0)
            return new CommonResult(200, "插入数据库成功 Port=" + serverPort, result);
        else
            return new CommonResult(444,"插入数据库失败 Port=" + serverPort, null);
    }
    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentBuId(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        //Log.info("插入结果--" + result);

        if(payment != null)
            return new CommonResult(200, "查询成功 Port=" + serverPort, payment);
        else
            return new CommonResult(444,"查询失败", null);
    }
    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for(String element : services){
            log.info("***" + element);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for(ServiceInstance instance : instances){
            log.info(instance.getInstanceId() + "---" + instance.getHost() + "---" + instance.getPort() + "---" + instance.getUri());
        }
        return this.discoveryClient;
    }
    @GetMapping(value = "/payment/lb")
    public String getServerPortLB(){
        return serverPort;
    }
    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout(){
        try{TimeUnit.SECONDS.sleep(3);}catch (InterruptedException e) {e.printStackTrace();}
        return serverPort;
    }
}
