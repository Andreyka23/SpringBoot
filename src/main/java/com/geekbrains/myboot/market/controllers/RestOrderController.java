package com.geekbrains.myboot.market.controllers;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.geekbrains.myboot.market.dto.CartDto;
import com.geekbrains.myboot.market.models.Order;
import com.geekbrains.myboot.market.models.Product;
import com.geekbrains.myboot.market.models.User;
import com.geekbrains.myboot.market.services.OrderService;
import com.geekbrains.myboot.market.services.UserService;
import com.geekbrains.myboot.market.utils.Cart;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;

@RestController
@RequestMapping("api/v1/orders")
@AllArgsConstructor
public class RestOrderController {
    private Cart cart;
    private OrderService orderService;
    private UserService userService;

    @PostMapping
    public String  createOrder(HttpServletRequest req) {

        // TODO : не получает параметры
        return  req.getParameter("address");
        // String userName=jsonParam.get("userName").toString()

//        User user = userService.findByUsername(username);
//        Order order = orderService.newOrder(user, username, phone, address);
//        cart.clear();
//        return order;
    }

}
