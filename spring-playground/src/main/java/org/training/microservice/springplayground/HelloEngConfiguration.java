package org.training.microservice.springplayground;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("hello-eng")
@Configuration
public class HelloEngConfiguration {

    @Bean("prof-hello")
    public IHello helloImpl() {
        return new HelloEng();
    }
}
