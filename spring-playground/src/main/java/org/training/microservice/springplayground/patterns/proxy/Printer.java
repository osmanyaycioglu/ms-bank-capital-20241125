package org.training.microservice.springplayground.patterns.proxy;

public class Printer {

    public String print(Person personParam) {
        return "Dear " + personParam.getName() + " " + personParam.getSurname();
    }

}
