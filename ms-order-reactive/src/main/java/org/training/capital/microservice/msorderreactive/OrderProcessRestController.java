package org.training.capital.microservice.msorderreactive;

import io.netty.channel.ChannelOption;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.training.capital.microservice.mscommon.error.ErrorObj;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.time.LocalTime;


@RestController
@RequiredArgsConstructor
@RefreshScope
public class OrderProcessRestController {
    private final WebClient.Builder webClientBuilder;

    @Value("${server.port}")
    private int port;

    @Value("${a.b.c}")
    private String abc;

    @GetMapping("/test")
    public String placeOrder(@RequestParam("name") String name) {
        return "Hello from : " + port + " : " + name + " abc : " + abc;
    }


    @PostMapping("/place")
    public Mono<OrderPlaceResponse> placeOrder(@RequestBody OrderPlaceRequest orderRequestParam) {
        HttpClient httpClientLoc = HttpClient.create()
                                             .option(ChannelOption.CONNECT_TIMEOUT_MILLIS,
                                                     2000);
        WebClient buildLoc = webClientBuilder.clientConnector(new ReactorClientHttpConnector(httpClientLoc))
                                             .defaultHeader("abc",
                                                            "osman")
                                             .build();
        OrderDetails orderDetailLoc = OrderDetails.builder()
                                                  .withCustomerNameParam(orderRequestParam.getCustomerName())
                                                  .withCustomerNumberParam(orderRequestParam.getCustomerNumber())
                                                  .withMealsParam(orderRequestParam.getMeals())
                                                  .build();
        return buildLoc.post()
                       .uri("http://RESTAURANT/api/v1/restaurant/menu/get/price")
                       .body(BodyInserters.fromValue(orderDetailLoc))
                       .retrieve()
                       .onStatus(HttpStatusCode::isError,
                                 r -> r.bodyToMono(ErrorObj.class)
                                       .flatMap(e -> Mono.error(new IllegalStateException(e.getErrorDescription()))))
                       .bodyToMono(PriceInfo.class)
                       .doOnNext(f -> System.out.println("Received : " + f))
                       .flatMap(f -> Mono.just(new OrderPlaceResponse("OK")));


    }

    @GetMapping(path = "/stream-flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamFlux() {
        return Flux.interval(Duration.ofSeconds(1))
                   .map(sequence -> "Flux - " + LocalTime.now()
                                                         .toString());
    }

}
