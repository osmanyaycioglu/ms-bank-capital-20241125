package org.training.capital.microservice.msorder.input.models;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.training.capital.microservice.msorder.input.validation.CheckNotValidWords;

@Data
public class MealDto {
    @NotBlank(message = "isim boş olamaz")
    @CheckNotValidWords(value = "abc")
    private String name;
    @DecimalMin("1")
    @DecimalMax("10")
    private Double portion;
}
