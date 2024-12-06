package org.training.capital.microservice.msorder.input;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.training.capital.microservice.msorder.input.models.OrderPlaceRequest;

@RequestMapping("/api/v1/order/process")
public interface IOrderProcessRestController {
    @PostMapping("/place")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "order yaratma",
            description = "order yaratma daha açıklamalı",
            responses = @ApiResponse(responseCode = "207", description = "tamamlandı gibi"))
    OrderPlaceResponse placeOrder(@Valid @RequestBody OrderPlaceRequest orderRequestParam);

    @GetMapping("/cancel")
    void cancelOrder(@RequestParam("orderId") String orderId);

    @GetMapping("/suspend/{oid}")
    public void suspendOrder(@PathVariable("oid") String orderId);

}
