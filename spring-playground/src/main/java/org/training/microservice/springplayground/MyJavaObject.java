package org.training.microservice.springplayground;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class MyJavaObject {
    private String name;
    private String version;
}

