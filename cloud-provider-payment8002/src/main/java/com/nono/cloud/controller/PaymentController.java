package com.nono.cloud.controller;

import com.nono.cloud.entities.CommonResult;
import com.nono.cloud.entities.Payment;
import com.nono.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;
    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        //Log.info("插入结果--" + result);

        if(result > 0)
            return new CommonResult(200, "插入数据库成功 Port=" + serverPort, result);
        else
            return new CommonResult(444,"插入数据库失败", null);
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
    @GetMapping(value = "/payment/lb")
    public String getServerPortLB(){
        return serverPort;
    }
}
