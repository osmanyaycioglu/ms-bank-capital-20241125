package org.training.capital.microservice.msorder.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.training.capital.microservice.msorder.facade.IOrderProcessFacade;
import org.training.capital.microservice.msorder.facade.models.CalculatedPrice;
import org.training.capital.microservice.msorder.facade.models.PaymentResult;
import org.training.capital.microservice.msorder.service.models.Order;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OrderProcessService {
    private final IOrderProcessFacade orderProcessFacade;


    public void placeOrder(Order orderParam) {
        Order           orderLoc =  orderProcessFacade.checkPreviousOrders(orderParam);
        CalculatedPrice priceLoc =  orderProcessFacade.getPriceInfo(orderParam);
        BigDecimal    bigDecimalLoc    = orderProcessFacade.calculateDiscount(orderProcessFacade);
        PaymentResult paymentResultLoc =  orderProcessFacade.orderPayment(orderParam);
    }

}
