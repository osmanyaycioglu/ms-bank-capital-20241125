package org.training.capital.microservice.mscustomeraggregator.integration;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.training.capital.microservice.mscustomeraggregator.models.Customer;

@Service
@RequiredArgsConstructor
public class CustomerIntegration {
    private final RestTemplate restTemplate;

    @Retry(name = "customer-int-add-customer-retry", fallbackMethod = "addCustomerFallback")
    @CircuitBreaker(name = "customer-int-add-customer-cb", fallbackMethod = "addCustomerFallback")
    public String addCustomer(final Customer customerParam) {
        return restTemplate.postForObject("http://customer/api/v1/customer/provision/add",
                                          customerParam,
                                          String.class);
    }


    public String addCustomerFallback(final Customer customerParam,
                                      Throwable throwableParam) {
        System.out.println("fallback");
        return "fallback result";
    }


    @Retry(name = "customer-int-rollback-customer-retry")
    public void rollBackCustomer(final String customerIdParam) {
        restTemplate.getForObject("http://customer/api/v1/customer/provision/rollback?customerId="
                                  + customerIdParam,
                                  String.class);

    }

    public void commitCustomer(final String customerIdParam) {
        restTemplate.getForObject("http://customer/api/v1/customer/provision/rollback?commit=" + customerIdParam,
                                  String.class);
    }
}
