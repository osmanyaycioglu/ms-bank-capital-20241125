package org.training.capital.microservice.mscustomer.db;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.training.capital.microservice.mscustomer.db.models.Customer;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerDao {
    private final ICustomerRepository customerRepository;


    public void insertCustomer(final Customer customerParam) {
        customerRepository.save(customerParam);
    }

    public List<Customer> getAllCustomersGreaterThan(final Integer weightParam) {
        return customerRepository.aggregateGreaterWeight(weightParam);
    }
}
