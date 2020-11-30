package ru.geekbrains.orders.service.controllers;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.dtos.ProductDto;

import java.util.List;

@FeignClient("products-service")
public interface ProductRequest {
    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/products")
    List<ProductDto> getProductsByIds(@RequestParam Long... ids);
}