package org.training.capital.microservice.msorder.facade;

import org.springframework.stereotype.Service;
import org.training.capital.microservice.msorder.facade.models.CalculatedPrice;
import org.training.capital.microservice.msorder.facade.models.PaymentResult;
import org.training.capital.microservice.msorder.service.models.Order;

import java.math.BigDecimal;

@Service
public class OrderProcessFacade implements IOrderProcessFacade {
    @Override
    public Order checkPreviousOrders(final Order orderParam) {
        return orderParam;
    }

    @Override
    public CalculatedPrice getPriceInfo(final Order orderParam) {

        return null;
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
