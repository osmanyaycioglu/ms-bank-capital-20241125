package org.training.capital.microservice.mscustomeraggregator.models;

import lombok.Data;

import java.util.List;

@Data
public class Customer {

    private String          firstName;
    private String          lastName;
    private Integer         weight;
    private Integer         height;
    private Long            visit  = 1L;
    private Address         address;
    private List<Phone>     phones;
    private List<Vote>      votes;
    private CustomerAccount customerAccount;
}
