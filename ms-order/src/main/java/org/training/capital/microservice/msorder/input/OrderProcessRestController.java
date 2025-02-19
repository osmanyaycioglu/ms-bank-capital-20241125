package org.training.capital.microservice.msorder.input;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import org.training.capital.microservice.msorder.facade.models.CalculatedPrice;
import org.training.capital.microservice.msorder.input.mappers.IOrderMapper;
import org.training.capital.microservice.msorder.input.models.MealDto;
import org.training.capital.microservice.msorder.input.models.OrderPlaceRequest;
import org.training.capital.microservice.msorder.input.models.Response;
import org.training.capital.microservice.msorder.service.OrderProcessService;


@RestController
@RequiredArgsConstructor
@RefreshScope
public class OrderProcessRestController implements IOrderProcessRestController {
    private final OrderProcessService orderProcessService;

    @Value("${server.port}")
    private int port;

    @Value("${a.b.c}")
    private String abc;

    @GetMapping("/test")
    public String placeOrder(@RequestParam("name") String name) {
        return "Hello from : " + port + " : "  + name + " abc : " + abc;
    }


    @PostMapping("/place")
    public OrderPlaceResponse placeOrder(@Valid @RequestBody OrderPlaceRequest orderRequestParam) {
        CalculatedPrice calculatedPriceLoc = orderProcessService.placeOrder(IOrderMapper.ORDER_MAPPER.toOrder(orderRequestParam));


        OrderPlaceResponse orderPlaceResponseLoc = new OrderPlaceResponse();
        orderPlaceResponseLoc.setResult(calculatedPriceLoc.getDescription()
                                        + " price : "
                                        + calculatedPriceLoc.getPrice());
        return orderPlaceResponseLoc;
    }

    @PostMapping("/place2")
    public OrderPlaceResponse placeOrder2(@Valid @RequestBody OrderPlaceRequest orderRequestParam) {
        CalculatedPrice calculatedPriceLoc = orderProcessService.placeOrder2(IOrderMapper.ORDER_MAPPER.toOrder(orderRequestParam));


        OrderPlaceResponse orderPlaceResponseLoc = new OrderPlaceResponse();
        orderPlaceResponseLoc.setResult(calculatedPriceLoc.getDescription()
                                        + " price : "
                                        + calculatedPriceLoc.getPrice());
        return orderPlaceResponseLoc;
    }

    @PostMapping("/place3")
    public OrderPlaceResponse placeOrder3(@Valid @RequestBody OrderPlaceRequest orderRequestParam) {
        CalculatedPrice calculatedPriceLoc = orderProcessService.placeOrder3(IOrderMapper.ORDER_MAPPER.toOrder(orderRequestParam));


        OrderPlaceResponse orderPlaceResponseLoc = new OrderPlaceResponse();
        orderPlaceResponseLoc.setResult(calculatedPriceLoc.getDescription()
                                        + " price : "
                                        + calculatedPriceLoc.getPrice());
        return orderPlaceResponseLoc;
    }


    public void cancelOrder(@RequestParam("orderId") String orderId) {
    }

    public void suspendOrder(@PathVariable("oid") String orderId) {
    }

//    @PostMapping("/operation/{op}")
//    public ResponseEntity<?> method(@PathVariable String op,
//                                    HttpServletRequest requestParam){
//        switch (op) {
//            case "add": {
//                ObjectMapper objectMapperLoc = new ObjectMapper();
//                MealDto mealDtoLoc = objectMapperLoc.readValue(requestParam.getInputStream(),
//                                                               MealDto.class);
//            }
//        }
//    }

}
