package org.training.capital.microservice.mscustomer.db;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.training.capital.microservice.mscustomer.aop.MethodTime;
import org.training.capital.microservice.mscustomer.db.models.Customer;

import javax.sql.DataSource;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerDao {
    private final ICustomerRepository customerRepository;
    private final IVoteRepository voteRepository;

    @MethodTime(tag = "customerDao.insertCustomer")
    @Transactional(propagation = Propagation.REQUIRED,
            isolation = Isolation.READ_COMMITTED)
    public void insertCustomer(final Customer customerParam) {
        customerRepository.save(customerParam);
        customerParam.getVotes().forEach(v -> v.setCustomerId(customerParam.getId()));
        voteRepository.saveAll(customerParam.getVotes());
    }

    public List<Customer> getAllCustomersGreaterThan(final Integer weightParam) {
        return customerRepository.aggregateGreaterWeight(weightParam);
    }
}
