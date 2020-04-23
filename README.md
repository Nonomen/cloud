---
title: SpringCloud 
tags: Java,SpringCloud
renderNumberedHeading: true
grammar_cjkRuby: true
---


这个笔记相当于这个Demo的目录。
# Eureka服务注册中心
相关Module
Eureka服务集群  cloud-eureka-server7001 cloud-eureka-server7002
客户端                 cloud-consumer-order80
微服务集群          cloud-provider-payment8001 cloud-provider-payment8002

@EnableDiscoveryClient      服务发现Discovery 通过服务发现获得服务信息
# 服务调用
## Ribbon本地负载均衡
相关Module
客户端                 cloud-consumer-order80

轮询算法 cloud-consumer-order80 com.nono.cloud.lb
## Openfeign本地负载均衡
相关Module
客户端                 cloud-consumer-feign-order80
# Hystrix断路器
相关Module
客户端                 cloud-consumer-feign-hystrix-order80
服务端					cloud-provider-hystrix-payment8001
图形化页面          cloud-consumer-hystrix-dashboard9001

服务降级
服务熔断
服务限流
# Gateway服务网关
相关Module
服务端                 cloud-gateway-gateway9527

非异步阻塞模型
路由配置
yml/代码
pom不需要actuator
Rredicate断言
Filter过滤器
# Config服务配置与服务总线
## Config分布式配置中心
相关Module
服务端                 cloud-config-center-3344
客户端                 cloud-config-client-3355 cloud-config-client-3366

动态刷新post请求 uir + /actuator/refresh
## Bus消息总线与RabbitMQ
相关Module
服务端                 cloud-stream-rabbitmq-provider8801
客户端                 cloud-stream-rabbitmq-consumer8802 cloud-stream-rabbitmq-consumer8803

RabbitMQ默认端口15672
动态刷新post请求 uir + /actuator/bus-refresh
## Stream消息驱动

Middleware中间件，支持RabbitMQ和Kafka
Binder 应用与消息中间件的封装（Kafka：topic，RabbitMQ：exchange）
@Input 输入通道
@Output 输出通道
@StreamListener 监听队列
@EnableBinding channel和exchange绑定
发布订阅模式
分组可解决重复消费与消息持久化
# Sleuth与Zipkin
相关Module
客户端                 cloud-consumer-order80
微服务集群          cloud-provider-payment8001

监控链路
默认port：9411