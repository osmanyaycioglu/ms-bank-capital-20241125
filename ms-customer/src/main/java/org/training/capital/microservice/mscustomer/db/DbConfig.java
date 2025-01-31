package org.training.capital.microservice.mscustomer.db;

import com.mongodb.MongoClientSettings;
import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.TransactionMetadata;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;

@Configuration
public class DbConfig {

    @Bean("mongoTrans")
    public MongoTransactionManager transactionManager(MongoDatabaseFactory databaseFactoryParam) {
        return new MongoTransactionManager(databaseFactoryParam);
    }

    @Bean("jpaTrans")
    public JpaTransactionManager transactionManagerCustomJpa() {
        return new JpaTransactionManager();

    }

    @Bean("transactionManager")
    public JpaTransactionManager transactionManagerJpa() {
        return new JpaTransactionManager();
    }


}
