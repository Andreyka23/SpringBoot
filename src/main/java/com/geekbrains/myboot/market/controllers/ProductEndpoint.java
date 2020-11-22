package com.geekbrains.myboot.market.controllers;

import com.geekbrains.myboot.market.services.ProductService;
import com.geekbrains.myboot.market.soap.orders.GetOrderRequest;
import com.geekbrains.myboot.market.soap.products.GetProductRequest;
import com.geekbrains.myboot.market.soap.products.GetProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://localhost/market/products";

    private final ProductService productService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductRequest")
    @ResponsePayload
    public GetProductResponse getCountry(@RequestPayload GetProductRequest request) {
        GetProductResponse response = new GetProductResponse();
        response.getProduct().addAll(productService.findAllSOAP());
        return response;
    }
}
