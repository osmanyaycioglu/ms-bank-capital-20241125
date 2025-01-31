package org.training.capital.microservice.mscustomer.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;
import org.training.capital.microservice.mscustomer.MyBean;
import org.training.capital.microservice.mscustomer.aop.MethodTime;
import org.training.capital.microservice.mscustomer.db.CustomerDao;
import org.training.capital.microservice.mscustomer.db.models.Customer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@RestController
@RequestMapping("/api/v1/customer/provision")
@RequiredArgsConstructor
public class CustomerProvisionController {
    private final CustomerDao customerDao;

    @Autowired
    private MyBean myBean;

    @MethodTime(tag = "controller.add")
    @PostMapping("/add")
    public String add(@RequestBody Customer customerParam){
        customerDao.insertCustomer(customerParam);
        return "OK";
    }

    @MethodTime(tag = "controller.getAllCustomersGreaterThan")
    @GetMapping("/find/greater/than/weight")
    public List<Customer> getAllCustomersGreaterThan(@RequestParam Integer weight){
        return customerDao.getAllCustomersGreaterThan(weight);
    }
}
