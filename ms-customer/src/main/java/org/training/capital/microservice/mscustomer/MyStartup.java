package org.training.capital.microservice.mscustomer;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
@RequiredArgsConstructor
public class MyStartup implements CommandLineRunner {
    private final MsCustomerApplication msCustomerApplication;
    @Override
    public void run(final String... args) throws Exception {

        Future<String> stringFutureLoc = msCustomerApplication.myAsyncMethod();
        System.out.println("MyStartup line 1 : "+ Thread.currentThread().getName());
        System.out.println("MyStartup line 2 : " + Thread.currentThread().getName());
        System.out.println("MyStartup line 3 : " + Thread.currentThread().getName());
        System.out.println("MyStartup line 4 : " + Thread.currentThread().getName());
        String sLoc = stringFutureLoc.get();
        System.out.println("I got result : " + sLoc );
    }
}
