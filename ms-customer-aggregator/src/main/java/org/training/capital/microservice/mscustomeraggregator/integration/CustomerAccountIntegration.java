package org.training.capital.microservice.mscustomeraggregator.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.training.capital.microservice.mscustomeraggregator.models.CustomerAccount;

@Service
@RequiredArgsConstructor
public class CustomerAccountIntegration {
    private final RestTemplate restTemplate;

    public Long addCustomerAccount(final CustomerAccount customerAccountParam) {
        return restTemplate.postForObject("http://customer-account/api/v1/account/management/add",
                                          customerAccountParam,
                                          Long.class);
    }

    public void rollbackCustomerAccount(final Long accountId) {
        restTemplate.getForObject("http://customer-account/api/v1/account/management/rollback?accountId=" + accountId,
                                  Long.class);

    }
}
