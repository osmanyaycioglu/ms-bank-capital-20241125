package org.training.capital.microservice.mscustomeraggregator.models;

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
