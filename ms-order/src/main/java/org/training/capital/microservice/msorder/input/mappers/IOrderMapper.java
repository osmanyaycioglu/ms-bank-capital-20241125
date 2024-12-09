package org.training.capital.microservice.msorder.input.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.training.capital.microservice.msorder.input.models.OrderPlaceRequest;
import org.training.capital.microservice.msorder.service.models.Order;

@Mapper
public interface IOrderMapper {

    IOrderMapper ORDER_MAPPER = Mappers.getMapper(IOrderMapper.class);

    Order toOrder(OrderPlaceRequest order);

    OrderPlaceRequest toOrderPlaceRequest(Order order);

}
