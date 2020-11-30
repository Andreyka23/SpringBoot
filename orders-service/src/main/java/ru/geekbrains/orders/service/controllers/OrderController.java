package ru.geekbrains.orders.service.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.dtos.OrderDto;
import ru.geekbrains.orders.service.services.OrderService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public List<OrderDto> getAllOrders() {
        return orderService.findAllOrders();
    }

    @GetMapping("/{id}")
    public Optional<OrderDto> getById(@PathVariable Long id) {
        return orderService.findById(id);
    }

    @PutMapping
    public void createOrder(@RequestParam Long[] ids) {
        orderService.createOrder(ids);
    }
}
