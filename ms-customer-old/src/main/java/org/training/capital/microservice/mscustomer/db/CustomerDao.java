package org.training.capital.microservice.mscustomer.db;

import jakarta.persistence.*;
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
    private final ICustomerRepository        customerRepository;
    private final IVoteRepository            voteRepository;
    private final ICustomerAccountRepository customerAccountRepository;

//    @PersistenceContext
//    private EntityManager entityManager;

//    @PersistenceUnit
//    private EntityManagerFactory entityManagerFactory;
//
//    public void jpaJob(Customer customerParam){
//        EntityManager entityManagerLoc = entityManagerFactory.createEntityManager();
//
//        entityManagerLoc.getTransaction().begin();
//        entityManagerLoc.persist(customerParam);
//        entityManagerLoc.find(Customer.class, 1, LockModeType.OPTIMISTIC);
//        entityManagerLoc.refresh(customerParam, LockModeType.PESSIMISTIC_READ);
//        Query queryLoc = null ;
//        queryLoc.setLockMode(LockModeType.PESSIMISTIC_FORCE_INCREMENT);
//
//        Customer mergeLoc = entityManagerLoc.merge(customerParam);
//        mergeLoc.setFirstName("osman");
//        entityManagerLoc.getTransaction().commit();
//        entityManagerLoc.close();
//
//    }

    @MethodTime(tag = "customerDao.insertCustomer")
    @Transactional(propagation = Propagation.REQUIRED,
            isolation = Isolation.READ_COMMITTED,
            transactionManager = "mongoTrans")
    public void insertCustomer(final Customer customerParam) {
        customerAccountRepository.save(customerParam.getCustomerAccount());
        customerRepository.save(customerParam);
        customerParam.getVotes()
                     .forEach(v -> v.setCustomerId(customerParam.getId()));
        voteRepository.saveAll(customerParam.getVotes());
    }

    public List<Customer> getAllCustomersGreaterThan(final Integer weightParam) {
        return customerRepository.aggregateGreaterWeight(weightParam);
    }
}
