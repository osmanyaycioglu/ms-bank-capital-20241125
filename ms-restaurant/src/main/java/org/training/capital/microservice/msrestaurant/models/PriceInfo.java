package org.training.capital.microservice.msrestaurant.models;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PriceInfo {
    private BigDecimal amount;
    private String description;
}

