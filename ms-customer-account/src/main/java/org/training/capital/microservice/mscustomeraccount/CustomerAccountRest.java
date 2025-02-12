package org.training.capital.microservice.mscustomeraccount;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/account/management")
@RequiredArgsConstructor
public class CustomerAccountRest {
    private final ICustomerAccountRepository customerAccountRepository;

    @PostMapping("/add")
    public Long addCustomerAccount(@RequestBody CustomerAccount customerAccountParam) {
        customerAccountRepository.save(customerAccountParam);
        return customerAccountParam.getAccountId();
    }

    @GetMapping("/rollback")
    public String rollbackAccount(@RequestParam Long accountId) {
        CustomerAccount customerAccountLoc = customerAccountRepository.findById(accountId)
                                                                      .orElseThrow(() -> new NoSuchElementException());
        customerAccountLoc.setStatus(4);
        customerAccountRepository.save(customerAccountLoc);
        return "OK";
    }

    @GetMapping("/commit")
    public String commitAccount(@RequestParam Long accountId) {
        CustomerAccount customerAccountLoc = customerAccountRepository.findById(accountId)
                                                                      .orElseThrow(() -> new NoSuchElementException());
        customerAccountLoc.setStatus(5);
        customerAccountRepository.save(customerAccountLoc);
        return "OK";
    }


}
