package org.training.capital.microservice.msnotificationkafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class MsNotificationKafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsNotificationKafkaApplication.class,
                              args);
    }

}
