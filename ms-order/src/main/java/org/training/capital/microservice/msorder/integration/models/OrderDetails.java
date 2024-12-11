package org.training.capital.microservice.msorder.integration.models;

import lombok.Data;

import java.util.List;

@Data
public class OrderDetails   {
  private List<MealInfo> meals;

}
