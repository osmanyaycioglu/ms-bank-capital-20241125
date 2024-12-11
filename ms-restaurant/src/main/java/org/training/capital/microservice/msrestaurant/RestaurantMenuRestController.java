package org.training.capital.microservice.msrestaurant;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.training.capital.microservice.msrestaurant.models.OrderDetails;
import org.training.capital.microservice.msrestaurant.models.PriceInfo;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/restaurant/menu")
public class RestaurantMenuRestController {

    @PostMapping("/get/price")
    public PriceInfo priceInfo(OrderDetails orderDetailsParam){
        PriceInfo priceInfoLoc = new PriceInfo();
        priceInfoLoc.setDescription("Desc : ");
        priceInfoLoc.setAmount(new BigDecimal(1000));
        return priceInfoLoc;
    }

}
