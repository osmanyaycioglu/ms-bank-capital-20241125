package org.training.microservice.springplayground;

import a.b.c.AbcConfiguration;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

//@SpringBootApplication(scanBasePackages = {"org.training.microservice.springplayground","a.b.c"})

@SpringBootApplication
@Import(AbcConfiguration.class)
@PropertySource("classpath:my.properties")
public class SpringPlaygroundApplication {

    @Autowired
    @Qualifier("myotherbean")
    private MyFirstBean abc;

    public static void main(String[] args) {
        SpringApplication.run(SpringPlaygroundApplication.class,
                              args);
    }

    @PostConstruct
    public void klm(){
        System.out.println("init");
    }

}
