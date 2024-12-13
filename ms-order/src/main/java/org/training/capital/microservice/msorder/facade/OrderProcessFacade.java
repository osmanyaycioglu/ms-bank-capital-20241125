package org.training.capital.microservice.msorder.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.training.capital.microservice.msorder.facade.models.CalculatedPrice;
import org.training.capital.microservice.msorder.facade.models.PaymentResult;
import org.training.capital.microservice.msorder.integration.RestaurantMenuIntegration;
import org.training.capital.microservice.msorder.service.models.Order;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OrderProcessFacade implements IOrderProcessFacade {
    private final RestaurantMenuIntegration restaurantMenuIntegration;

    @Override
    public Order checkPreviousOrders(final Order orderParam) {
        return orderParam;
    }

    @Override
    public CalculatedPrice getPriceInfo(final Order orderParam) {
        return restaurantMenuIntegration.getPriceInfo(orderParam);
    }

    @Override
    public CalculatedPrice getPriceInfo2(final Order orderParam) {
        return restaurantMenuIntegration.getPriceInfo2(orderParam);
    }

    @Override
    public CalculatedPrice getPriceInfo3(final Order orderParam) {
        return restaurantMenuIntegration.getPriceInfo3(orderParam);
    }

    @Override
    public BigDecimal calculateDiscount(final IOrderProcessFacade orderProcessFacadeParam) {
        return null;
    }

    @Override
    public PaymentResult orderPayment(final Order orderParam) {
        return null;
    }
}
