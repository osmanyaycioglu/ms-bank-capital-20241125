package org.training.capital.microservice.mscustomer.db.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vote {
    @Id
    @Generated
    private ObjectId id;
    private String   customerName;
    private Integer  vote;
    private ObjectId   customerId;

}
