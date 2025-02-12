package org.training.capital.microservice.mscustomeraggregator;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.training.capital.microservice.mscustomeraggregator.integration.CustomerIntegration;
import org.training.capital.microservice.mscustomeraggregator.models.Customer;
import org.training.capital.microservice.mscustomeraggregator.services.CustomerProvisionService;

@RestController
@RequestMapping("/api/v1/customer/provision")
@RequiredArgsConstructor
public class CustomerProvisionRest {
    private final CustomerProvisionService customerProvisionService;


    @PostMapping("/add")
    public String add(@RequestBody Customer customerParam) {
        // customerProvisionService.add(customerParam);
        return "OK";
    }

}
