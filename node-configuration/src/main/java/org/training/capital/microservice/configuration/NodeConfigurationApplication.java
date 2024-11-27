package org.training.capital.microservice.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableConfigServer
@EnableScheduling
@SpringBootApplication
public class NodeConfigurationApplication {

    public static void main(String[] args) {
        SpringApplication.run(NodeConfigurationApplication.class,
                              args);
    }

    @Scheduled(initialDelay = 5000,fixedDelay = 5000)
    public void runMe(){
        System.out.println("tekrar");
    }

}
