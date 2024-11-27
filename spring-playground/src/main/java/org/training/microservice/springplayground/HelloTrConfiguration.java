package org.training.microservice.springplayground;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("hello-tr")
@Configuration
public class HelloTrConfiguration {

    @Bean("prof-hello")
    public IHello helloImpl(){
            return new HelloTr();
    }
}
