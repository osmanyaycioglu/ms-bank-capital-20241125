package org.training.capital.microservice.mscustomer.db.models;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
public class Customer {

    @Id
    private ObjectId    id;
    private String      firstName;
    private String      lastName;
    private Integer     weight;
    private Integer     height;
    private Long        visit = 1L;
    private Address     address;
    private List<Phone> phones;
    @Transient
    private List<Vote>  votes;
}
