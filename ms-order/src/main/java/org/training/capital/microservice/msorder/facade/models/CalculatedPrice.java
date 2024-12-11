package org.training.capital.microservice.msorder.facade.models;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CalculatedPrice {
    private String     description;
    private BigDecimal price;
}
