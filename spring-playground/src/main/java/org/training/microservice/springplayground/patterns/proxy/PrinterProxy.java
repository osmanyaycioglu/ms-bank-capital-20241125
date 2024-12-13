package org.training.microservice.springplayground.patterns.proxy;

public class PrinterProxy extends Printer{

    @Override
    public String print(final Person personParam) {
        String printLoc = super.print(personParam);
        return printLoc.toUpperCase();
    }
}
