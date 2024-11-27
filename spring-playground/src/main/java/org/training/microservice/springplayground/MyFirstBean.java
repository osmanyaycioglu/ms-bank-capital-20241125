package org.training.microservice.springplayground;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Lazy
@Component
@RequiredArgsConstructor
public class MyFirstBean {
    private final MyJavaObject myJavaObject;

}
