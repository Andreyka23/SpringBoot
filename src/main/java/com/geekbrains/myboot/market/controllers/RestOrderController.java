package com.geekbrains.myboot.market.controllers;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.geekbrains.myboot.market.dto.CartDto;
import com.geekbrains.myboot.market.dto.OrderDto;
import com.geekbrains.myboot.market.exceptions.ResourceNotFoundException;
import com.geekbrains.myboot.market.models.Order;
import com.geekbrains.myboot.market.models.Product;
import com.geekbrains.myboot.market.models.User;
import com.geekbrains.myboot.market.services.OrderService;
import com.geekbrains.myboot.market.services.UserService;
import com.geekbrains.myboot.market.utils.Cart;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class RestOrderController {
    private final Cart cart;
    private final OrderService orderService;
    private final UserService userService;

    @GetMapping
    public List<OrderDto> getAllOrders(Principal principal) {
        return orderService.findAllUserOrdersDtosByUsername(principal.getName());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewOrder(Principal principal, @RequestParam String username, @RequestParam String phone, @RequestParam String address) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Unable to create order for user: " + principal.getName() + ". User doesn't exist"));
        Order order = new Order(user, cart, phone, address);
        orderService.save(order);
        cart.clear();
    }

}
