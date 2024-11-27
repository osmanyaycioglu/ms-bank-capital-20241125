package org.training.microservice.springplayground;

public class HelloEng implements IHello {

    @Override
    public String hello() {
        return "hello";
    }

    @Override
    public String goodbye() {
        return "goodbye";
    }

}
