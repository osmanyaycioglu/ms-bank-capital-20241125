package org.training.capital.microservice.mscustomer.db;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;

@Configuration
public class DbConfig {

    @Bean("mongoTrans")
    public MongoTransactionManager transactionManager(MongoDatabaseFactory databaseFactoryParam) {
        return new MongoTransactionManager(databaseFactoryParam);
    }


}
