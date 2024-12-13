package org.training.capital.microservice.msorder.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.training.capital.microservice.msorder.integration.models.OrderDetails;
import org.training.capital.microservice.msorder.integration.models.PriceInfo;

@FeignClient(value = "RESTAURANT",contextId = "1")
public interface IRestaurantMenuFeignClient {

    @PostMapping("/api/v1/restaurant/menu/get/price")
    PriceInfo priceInfo(OrderDetails orderDetailsParam);
}
