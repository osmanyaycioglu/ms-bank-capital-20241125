package org.training.capital.microservice.apigworder;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class MyGlobalFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(final ServerWebExchange exchange,
                             final GatewayFilterChain chain) {
        System.out.println("------------- Global Request Filter ---------------");
        return chain.filter(exchange);
    }
}
