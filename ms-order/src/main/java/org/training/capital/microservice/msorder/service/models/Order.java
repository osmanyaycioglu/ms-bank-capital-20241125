package org.training.capital.microservice.msorder.service.models;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

@Data
public class Order {

    private String        orderId;
    private String        customerName;
    private String        customerNumber;
    private Integer       deliveryMinutes;
    private ZonedDateTime scheduleTime;
    private List<Meal> meals;
    private EOrderStatus orderStatus;
    private ZonedDateTime orderAcceptTime;
    private ZonedDateTime orderCompleteTime;
    private ZonedDateTime orderSentTime;

}
