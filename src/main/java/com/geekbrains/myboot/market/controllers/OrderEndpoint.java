package com.geekbrains.myboot.market.controllers;

import com.geekbrains.myboot.market.services.OrderService;
import com.geekbrains.myboot.market.soap.orders.GetOrderRequest;
import com.geekbrains.myboot.market.soap.orders.GetOrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class OrderEndpoint {

    private static final String NAMESPACE_URI = "http://localhost/market/orders";

    private final OrderService orderService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getOrderRequest")
    @ResponsePayload
    public GetOrderResponse getOrders(@RequestPayload GetOrderRequest request) {
        GetOrderResponse response = new GetOrderResponse();
        response.getOrder().addAll(orderService.findSoapByUsername(request.getUsername()));
        return response;
    }
}
