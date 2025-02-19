package org.training.capital.microservice.apigworder;

import lombok.Data;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;
import java.util.function.Predicate;

@Component
public class TokenCheckRoutePredicateFactory extends AbstractRoutePredicateFactory<TokenCheckRoutePredicateFactory.TokenCheckConfig> {


    public TokenCheckRoutePredicateFactory() {
        super(TokenCheckConfig.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(final TokenCheckConfig config) {
        return swe -> {
            List<String> stringsLoc = swe.getRequest()
                                         .getHeaders()
                                         .get("Authorization");
            if (stringsLoc != null && !stringsLoc.isEmpty()) {
                String token = stringsLoc.get(0);
                System.out.println("Token : " + token + " config : " + config);
                return true;
            }
            System.out.println("No Token : " + config);
            return true;
        } ;
    }

    @Data
    public static class TokenCheckConfig {
        boolean check = false;
        String  subject;
    }


}
