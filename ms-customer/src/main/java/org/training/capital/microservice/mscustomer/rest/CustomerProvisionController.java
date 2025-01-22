package org.training.capital.microservice.mscustomer.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.training.capital.microservice.mscustomer.aop.MethodTime;
import org.training.capital.microservice.mscustomer.db.CustomerDao;
import org.training.capital.microservice.mscustomer.db.models.Customer;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer/provision")
@RequiredArgsConstructor
public class CustomerProvisionController {
    private final CustomerDao customerDao;

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
