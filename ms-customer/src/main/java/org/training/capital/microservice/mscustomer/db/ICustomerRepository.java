package org.training.capital.microservice.mscustomer.db;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.training.capital.microservice.mscustomer.db.models.Customer;

import java.util.List;

public interface ICustomerRepository extends MongoRepository<Customer,String> {


    @Aggregation(pipeline = {"""
        {
            "$project": {
                "firstName": 1,
                "lastName": 1,
                "height": 1,
                "weight": 1
            }
        }
        """,
        """
        {
            "$match": {
                "weight": { "$gte": ?0}
            }
        }
"""})
    List<Customer> aggregateGreaterWeight(Integer weight);

    List<Customer> findByHeightBetween(Integer low,Integer high);

    @Query("{ 'firstName' : ?0 }")
    List<Customer> searchXYZ(String name);

}
