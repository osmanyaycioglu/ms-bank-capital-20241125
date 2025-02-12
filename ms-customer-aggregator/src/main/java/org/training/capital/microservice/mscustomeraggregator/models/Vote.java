package org.training.capital.microservice.mscustomeraggregator.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vote {
    private String   customerName;
    private Integer  vote;
}
