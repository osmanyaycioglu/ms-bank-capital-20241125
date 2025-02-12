package org.training.capital.microservice.mscustomeraggregator.services;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.training.capital.microservice.mscustomeraggregator.integration.CustomerAccountIntegration;
import org.training.capital.microservice.mscustomeraggregator.integration.CustomerIntegration;
import org.training.capital.microservice.mscustomeraggregator.models.Customer;

@Service
@RequiredArgsConstructor
public class CustomerProvisionService {
    private static final Logger                     logger = LoggerFactory.getLogger(CustomerProvisionService.class);
    private final        CustomerIntegration        customerIntegration;
    private final        CustomerAccountIntegration customerAccountIntegration;

    public void add(final Customer customerParam) {
        String customerId = null;
        Long   accountId  = null;
        try {
            customerId = customerIntegration.addCustomer(customerParam);
            customerParam.getCustomerAccount()
                         .setCustomerId(customerId);
            accountId = customerAccountIntegration.addCustomerAccount(customerParam.getCustomerAccount());

            try {
                customerIntegration.commitCustomer(customerId);
            } catch (Exception exp) {
                logger.error("[CustomerProvisionService][add]-> *Error* : " + exp.getMessage(),
                             exp);
                rollback(customerId,
                         accountId);
                throw exp;
            }

            try {
                customerIntegration.commitCustomer(customerId);
            } catch (Exception exp) {
                logger.error("[CustomerProvisionService][add]-> *Error* : " + exp.getMessage(),
                             exp);
                rollback(customerId,
                         accountId);
                throw exp;
            }

        } catch (Exception exp) {
            logger.error("[CustomerProvisionService][add]-> *Error* : " + exp.getMessage(),
                         exp);
            rollback(customerId,
                     accountId);

        }

    }

    private void rollback(final String customerId,
                          final Long accountId) {
        if (customerId != null) {
            try {
                customerIntegration.rollBackCustomer(customerId);
            } catch (Exception exp) {
                logger.error("[CustomerProvisionService][rollback]-> *Error* : " + exp.getMessage(),
                             exp);
            }

        }
        if (accountId != null) {
            try {
                customerAccountIntegration.rollbackCustomerAccount(accountId);
            } catch (Exception exp) {
                logger.error("[CustomerProvisionService][rollback]-> *Error* : " + exp.getMessage(),
                             exp);
            }
        }
    }
}
