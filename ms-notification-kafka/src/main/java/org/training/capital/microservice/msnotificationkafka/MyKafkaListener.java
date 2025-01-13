package org.training.capital.microservice.msnotificationkafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MyKafkaListener {

    @KafkaListener(topics = "topic1",concurrency = "2")
    public void handleSMS(String message){
    }

}
