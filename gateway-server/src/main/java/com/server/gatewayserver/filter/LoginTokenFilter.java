package com.server.gatewayserver.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class LoginTokenFilter implements GlobalFilter, Ordered {

    private static final String ACCESS_KEY = "ASSESS_TOKEN";

    private static final int order = -10;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
      /*  if (!exchange.getRequest().getHeaders().containsKey(ACCESS_KEY)) {
            throw new AuthException("request not have token, server refusal visit", Result.NO_TOKEN());
        }*/
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return order;
    }

}
