package com.nono.cloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalancer {
    //获取服务列表
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
