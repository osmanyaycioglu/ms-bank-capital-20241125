package org.training.capital.microservice.msorder.integration.mappings;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.training.capital.microservice.msorder.facade.models.CalculatedPrice;
import org.training.capital.microservice.msorder.integration.models.OrderDetails;
import org.training.capital.microservice.msorder.integration.models.PriceInfo;
import org.training.capital.microservice.msorder.service.models.Order;

@Mapper
public interface IOrderInfoMapping {
    IOrderInfoMapping ORDER_INFO_MAPPING = Mappers.getMapper(IOrderInfoMapping.class);

    OrderDetails toOrderDetail(Order order);

    @Mapping(source = "amount",target = "price")
    CalculatedPrice toGetPriceResult(PriceInfo priceInfoParam);
}
