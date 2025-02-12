package org.training.capital.microservice.mscustomer.db.models;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountRootInfo {
    private BigDecimal balance;
    private BigDecimal bonus;

}
