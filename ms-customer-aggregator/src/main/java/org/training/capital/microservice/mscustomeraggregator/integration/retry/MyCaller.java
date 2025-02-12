package org.training.capital.microservice.mscustomeraggregator.integration.retry;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.core.Registry;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.training.capital.microservice.mscustomeraggregator.models.Customer;


//@Component
@RequiredArgsConstructor
public class MyCaller implements CommandLineRunner {
    private final MyCallee               myCallee;
    private final RetryRegistry          retryRegistry;
    private final CircuitBreakerRegistry circuitBreakerRegistry;

    private class RetryCounter {
        long retryCount;

        long incAndGet() {
            return ++retryCount;
        }
    }

    @Override
    public void run(final String... args) throws Exception {
        CircuitBreaker circuitBreakerLoc = circuitBreakerRegistry.circuitBreaker("customer-int-add-customer-cb");


        RetryCounter retryCounterLoc = new RetryCounter();

        Retry retryLoc = retryRegistry.retry("customer-int-add-customer-retry");

        Retry.Metrics        metricsLoc        = retryLoc.getMetrics();
        Retry.EventPublisher eventPublisherLoc = retryLoc.getEventPublisher();
        eventPublisherLoc.onRetry(event -> System.out.println("Retried : "
                                                              + event.getEventType()
                                                              + " retryCount : "
                                                              + retryCounterLoc.incAndGet()));

        CircuitBreaker.EventPublisher cbEventPublisherLoc = circuitBreakerLoc.getEventPublisher();
        cbEventPublisherLoc.onCallNotPermitted(e -> System.out.println("*** Not permitted : " + e));
        for (int i = 0; i < 50; i++) {
            Customer customerLoc = new Customer();
            System.out.println("----------------- CALL : " + i + " ----------------");
            try {
                myCallee.addCustomer(customerLoc);
                Thread.sleep(500);
            } catch (Exception exp) {
                System.out.println("Error : " + exp.getMessage());
            }

            CircuitBreaker.Metrics cbMetricsLoc = circuitBreakerLoc.getMetrics();

            System.out.println("[CB] state : "
                               + circuitBreakerLoc.getState()
                               + " s : "
                               + cbMetricsLoc.getNumberOfSuccessfulCalls()
                               + " np : "
                               + cbMetricsLoc.getNumberOfNotPermittedCalls()
                               + " f : "
                               + cbMetricsLoc.getNumberOfFailedCalls()
                               + " sl : "
                               + cbMetricsLoc.getNumberOfSlowCalls()
                               + " sls : "
                               + cbMetricsLoc.getNumberOfSlowSuccessfulCalls()
                               + " sls : "
                               + cbMetricsLoc.getNumberOfSlowFailedCalls()
            );

            System.out.println("[RETRY] swr : "
                               + metricsLoc.getNumberOfSuccessfulCallsWithRetryAttempt()
                               + " s : "
                               + metricsLoc.getNumberOfSuccessfulCallsWithoutRetryAttempt()
                               + " f : "
                               + metricsLoc.getNumberOfFailedCallsWithoutRetryAttempt()
                               + " fwr : "
                               + metricsLoc.getNumberOfFailedCallsWithRetryAttempt()
                               + " total : "
                               + metricsLoc.getNumberOfTotalCalls()
            );
            System.out.println("----------------- !0! ----------------");

        }
    }
}
