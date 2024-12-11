package org.training.capital.microservice.msorder.facade;

import org.training.capital.microservice.msorder.facade.models.CalculatedPrice;
import org.training.capital.microservice.msorder.facade.models.PaymentResult;
import org.training.capital.microservice.msorder.service.models.Order;

import java.math.BigDecimal;

public interface IOrderProcessFacade {
    Order checkPreviousOrders(Order orderParam);

    CalculatedPrice getPriceInfo(Order orderParam);

    BigDecimal calculateDiscount(IOrderProcessFacade orderProcessFacadeParam);

    PaymentResult orderPayment(Order orderParam);
}
