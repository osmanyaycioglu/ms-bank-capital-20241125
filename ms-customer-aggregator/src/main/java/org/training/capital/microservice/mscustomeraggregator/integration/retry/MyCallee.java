package org.training.capital.microservice.mscustomeraggregator.integration.retry;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Component;
import org.training.capital.microservice.mscustomeraggregator.models.Customer;

@Component
public class MyCallee {

    private long counter = 0;
    private long fallCounter = 0;


    @Retry(name = "customer-int-add-customer-retry", fallbackMethod = "addCustomerFallback")
    @CircuitBreaker(name = "customer-int-add-customer-cb", fallbackMethod = "addCustomerFallback")
    public String addCustomer(final Customer customerParam) {
        counter++;
        System.out.println("addCustomer : " + counter);
        if (counter < 25) {
            if (counter % 3 == 0) {
                throw new IllegalArgumentException("test");
            }
        } else {
            throw new IllegalArgumentException("test");
        }
        return "OK";
    }

    public String addCustomerFallback(final Customer customerParam,
                                      Throwable throwableParam) {
        System.out.println("fallback : " + throwableParam.getClass());
        fallCounter++;
        if (fallCounter % 3 == 0) {
            throw new IllegalArgumentException("fallback test");
        }
        return "fallback result";
    }

}
