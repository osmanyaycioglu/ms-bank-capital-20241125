package org.training.capital.microservice.mscustomer.db.models;


import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class AccountInfo {
    @Id
    @GeneratedValue
    private Long infoId;
    private String     location;
    private BigDecimal ratio;
}
