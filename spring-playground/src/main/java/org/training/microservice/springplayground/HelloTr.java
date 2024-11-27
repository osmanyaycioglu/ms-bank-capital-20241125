package org.training.microservice.springplayground;

public class HelloTr implements IHello {

    @Override
    public String hello() {
        return "merhaba";
    }

    @Override
    public String goodbye() {
        return "güle güle";
    }

}
