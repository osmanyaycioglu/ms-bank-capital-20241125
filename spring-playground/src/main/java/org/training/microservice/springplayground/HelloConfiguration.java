package org.training.microservice.springplayground;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloConfiguration {

    @Bean
    public IHello helloImpl(@Value("${app.language}") String language){
        return switch (language) {
            case "tr" -> new HelloTr();
            default -> new HelloEng();
        };
    }
}
