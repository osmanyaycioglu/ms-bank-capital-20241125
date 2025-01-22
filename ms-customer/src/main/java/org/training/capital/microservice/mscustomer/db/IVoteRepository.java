package org.training.capital.microservice.mscustomer.db;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.training.capital.microservice.mscustomer.db.models.Vote;

public interface IVoteRepository extends MongoRepository<Vote,String> {
}
