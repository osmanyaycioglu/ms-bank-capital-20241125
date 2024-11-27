package org.training.microservice.springplayground.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class MyController {

    //@RequestMapping(method = RequestMethod.GET,value = "/hello/deneme")
    @GetMapping("/deneme")
    public String hello(){
        return "hello world";
    }

}
