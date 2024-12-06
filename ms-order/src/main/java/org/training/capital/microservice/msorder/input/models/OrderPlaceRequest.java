package org.training.capital.microservice.msorder.input.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.training.capital.microservice.msorder.input.validation.CheckNotValidWords;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@CheckNotValidWords({"abc","123","xyz"})
public class OrderPlaceRequest {
    @NotBlank
    @Size(min = 10, max = 20)
    private String orderId;
    @NotEmpty
    @Size(min = 2, max = 15)
    private String customerName;
    @NotNull
    @CheckNotValidWords({"qwerty","123","xyz"})
    private String customerNumber;
    @Min(10)
    @Max(50)
    private Integer deliveryMinutes;
    @Future
    private ZonedDateTime scheduleTime;
    @Valid
    @Size(min = 1)
    private List<MealDto> meals;
}
