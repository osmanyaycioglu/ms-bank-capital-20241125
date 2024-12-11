package org.training.capital.microservice.msorder.integration.models;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PriceInfo   {
  private BigDecimal amount      ;
  private String     description ;
}