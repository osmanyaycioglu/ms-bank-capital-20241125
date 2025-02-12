package org.training.capital.microservice.mscustomer.db;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class MyChainedTransactionConfig {

    @Bean("ctTrans")
    public PlatformTransactionManager platformTransactionManager(@Qualifier("mongoTrans") MongoTransactionManager mongoTransactionManagerParam,
                                                                 @Qualifier("jpaTrans") JpaTransactionManager jpaTransactionManagerParam) {
        return new ChainedTransactionManager(jpaTransactionManagerParam,
                                             mongoTransactionManagerParam);
    }

}
