package org.training.microservice.springplayground.patterns.proxy;

public class ProxyMain {
    public static void main(String[] args) {
        Person personLoc = new Person();
        personLoc.setName("osman");
        personLoc.setSurname("yaycıoğlu");
        Printer printerLoc = new PrinterProxy();
        String  printLoc   = printerLoc.print(personLoc);
        System.out.println(printLoc);
    }
}
