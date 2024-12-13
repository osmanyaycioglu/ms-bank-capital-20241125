package org.training.capital.microservice.msrestaurant.models;

import lombok.Data;

import java.util.List;

@Data
public class OrderDetails {
    private List<MealInfo> meals;
    private String        customerName;
    private String        customerNumber;

}
