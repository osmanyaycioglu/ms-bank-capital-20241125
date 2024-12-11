package org.training.capital.microservice.msorder.facade.models;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentResult {
    private String     transactionId;
    private BigDecimal amount;
    private String     description;
}
