package org.training.capital.microservice.mscustomeraggregator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.client.RestTemplate;
import org.training.capital.microservice.mscommon.error.ErrorConfig;

@SpringBootApplication
@Import(ErrorConfig.class)
@EnableMethodSecurity(prePostEnabled = true)
public class MsCustomerAggregatorApplication {

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(MsCustomerAggregatorApplication.class,
                              args);
    }

}
