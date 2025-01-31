package org.training.capital.microservice.mscustomer;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
// @RequestScope
// @Scope("prototype")
public class MyBean {
    public void test(){
    }
}
