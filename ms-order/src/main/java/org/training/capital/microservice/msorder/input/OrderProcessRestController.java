package org.training.capital.microservice.msorder.input;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.training.capital.microservice.msorder.input.models.MealDto;
import org.training.capital.microservice.msorder.input.models.OrderPlaceRequest;
import org.training.capital.microservice.msorder.input.models.Response;

@RestController
public class OrderProcessRestController implements IOrderProcessRestController {

    public OrderPlaceResponse placeOrder(@Valid @RequestBody OrderPlaceRequest orderRequestParam) {
        return null;
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
