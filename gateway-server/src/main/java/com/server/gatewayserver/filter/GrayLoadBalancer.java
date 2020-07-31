package com.server.gatewayserver.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.gateway.config.LoadBalancerProperties;
import org.springframework.cloud.gateway.filter.LoadBalancerClientFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;

import java.net.URI;

@Slf4j
public class GrayLoadBalancer extends LoadBalancerClientFilter {

    public GrayLoadBalancer(LoadBalancerClient loadBalancer, LoadBalancerProperties properties) {
        super(loadBalancer, properties);
    }

    @Override
    protected ServiceInstance choose(ServerWebExchange exchange) {
        HttpHeaders headers = exchange.getRequest().getHeaders();
        RibbonLoadBalancerClient ribbonLoadBalancerClient = (RibbonLoadBalancerClient) this.loadBalancer;
        return ribbonLoadBalancerClient.choose(((URI) exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR)).getHost(), headers);
    }
}
