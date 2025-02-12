package org.training.capital.microservice.mscustomeraggregator.models;

import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
public class CustomerAccount {

    private String accountName;
    private Integer discount;
    private BigDecimal balance;
    private BigDecimal bonus;
    private AccountInfo accountInfo;
    private String customerId;



}
