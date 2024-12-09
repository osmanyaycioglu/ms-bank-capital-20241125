package org.training.capital.microservice.msorder.service.models;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;

@Data
public class Meal {
    private String name;
    private Double portion;

}
