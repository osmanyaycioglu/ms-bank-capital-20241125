package org.training.microservice.springplayground.patterns.observer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alarm {
    private String alarmId;
    private String alarmType;
}
