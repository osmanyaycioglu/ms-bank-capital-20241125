package org.training.capital.microservice.msorder.integration.models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OrderDetails {
    private List<MealInfo> meals;
    private String         customerName;
    private String         customerNumber;

    @Builder(setterPrefix = "with")
    public OrderDetails(final List<MealInfo> mealsParam,
                        final String customerNameParam,
                        final String customerNumberParam) {
        meals          = mealsParam;
        customerName   = customerNameParam;
        customerNumber = customerNumberParam;
    }
}
