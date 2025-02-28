package org.training.capital.microservice.msorderreactive;

import org.reactivestreams.Publisher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class MsOrderReactiveApplication {

    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder(){
        return WebClient.builder();
    }

    public static void main(String[] args) {
        SpringApplication.run(MsOrderReactiveApplication.class,
                              args);

//        Publisher<String> stringMonoLoc = Mono.just("osman");
//
//        MySubscriber mySubscriberLoc = new MySubscriber();
//
//        stringMonoLoc.subscribe(mySubscriberLoc);
//
//        StepVerifier.create(stringMonoLoc)
//                    .expectNext("osman")
//                    .verifyComplete();
//
//        Flux<String> stringFluxLoc = Flux.fromIterable(List.of("osman",
//                                                               "mehmet",
//                                                               "ali"));
//        StepVerifier.create(stringFluxLoc)
//                    .expectNext("osman","mehmet","ali")
//                    .verifyComplete();
//
//
//        List.of("osman",
//                "mehmet",
//                "ali")
//            .parallelStream()
//            .distinct()
//            .sorted()
//            .filter(s -> s.length() > 4)
//            .collect(Collectors.toList());

    }

}
