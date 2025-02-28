package org.training.capital.microservice.msorderreactive;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

@Data
public class OrderPlaceRequest {
    private String customerName;
    private String customerNumber;
    private Integer deliveryMinutes;
    private ZonedDateTime scheduleTime;
    private List<MealInfo> meals;
}
