package org.training.capital.microservice.mscustomeraggregator.models;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
public class AccountInfo {
    private Long infoId;
    private String     location;
    private BigDecimal ratio;
}
