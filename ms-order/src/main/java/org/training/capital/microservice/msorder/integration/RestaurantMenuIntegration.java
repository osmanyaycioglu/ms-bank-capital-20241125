package org.training.capital.microservice.msorder.integration;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.training.capital.microservice.msorder.facade.models.CalculatedPrice;
import org.training.capital.microservice.msorder.integration.mappings.IOrderInfoMapping;
import org.training.capital.microservice.msorder.integration.models.OrderDetails;
import org.training.capital.microservice.msorder.integration.models.PriceInfo;
import org.training.capital.microservice.msorder.service.models.Order;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantMenuIntegration {
    private final RestTemplate               restTemplate;
    private final EurekaClient               eurekaClient;
    private final IRestaurantMenuFeignClient restaurantMenuFeignClient;

    private long index = 0;

    public CalculatedPrice getPriceInfo(Order orderParam) {
        OrderDetails orderDetailLoc = IOrderInfoMapping.ORDER_INFO_MAPPING.toOrderDetail(orderParam);
        PriceInfo priceInfoLoc = restTemplate.postForObject("http://RESTAURANT/api/v1/restaurant/menu/get/price",
                                                            orderDetailLoc,
                                                            PriceInfo.class);
        return IOrderInfoMapping.ORDER_INFO_MAPPING.toGetPriceResult(priceInfoLoc);
    }

    public CalculatedPrice getPriceInfo2(Order orderParam) {
        OrderDetails       orderDetailLoc = IOrderInfoMapping.ORDER_INFO_MAPPING.toOrderDetail(orderParam);
        Application        applicationLoc = eurekaClient.getApplication("RESTAURANT");
        List<InstanceInfo> instancesLoc   = applicationLoc.getInstances();
        index++;
        int          currentIndex    = (int) (index % instancesLoc.size());
        InstanceInfo instanceInfoLoc = instancesLoc.get(currentIndex);
        RestTemplate restTemplateLoc = new RestTemplate();
        PriceInfo priceInfoLoc = restTemplateLoc.postForObject("http://"
                                                               + instanceInfoLoc.getIPAddr()
                                                               + ":"
                                                               + instanceInfoLoc.getPort()
                                                               + "/api/v1/restaurant/menu/get/price",
                                                               orderDetailLoc,
                                                               PriceInfo.class);
        return IOrderInfoMapping.ORDER_INFO_MAPPING.toGetPriceResult(priceInfoLoc);
    }

    public CalculatedPrice getPriceInfo3(Order orderParam) {
        OrderDetails orderDetailLoc = IOrderInfoMapping.ORDER_INFO_MAPPING.toOrderDetail(orderParam);
        PriceInfo    priceInfoLoc   = restaurantMenuFeignClient.priceInfo(orderDetailLoc);
        return IOrderInfoMapping.ORDER_INFO_MAPPING.toGetPriceResult(priceInfoLoc);
    }

}
