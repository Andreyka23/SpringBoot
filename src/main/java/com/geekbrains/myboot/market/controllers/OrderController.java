package com.geekbrains.myboot.market.controllers;

import com.geekbrains.myboot.market.models.Order;
import com.geekbrains.myboot.market.models.User;
import com.geekbrains.myboot.market.services.OrderService;
import com.geekbrains.myboot.market.services.UserService;
import com.geekbrains.myboot.market.utils.Cart;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private UserService userService;
    private OrderService orderService;
    private Cart cart;

    @GetMapping
    public String showAllOrders(Principal principal, Model model ) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("orders", orderService.findByUser(user));
        return "orders";
    }

    @GetMapping("/create")
    public String showOrderPage(Principal principal, Model model) {
        model.addAttribute("username", principal.getName());
        return "checkout";
    }

    @PostMapping("/new")
    public String editProduct(Principal principal,
                              @RequestParam(name = "name") String name,
                              @RequestParam(name = "phone") String phone,
                              @RequestParam(name = "address") String address ) {
        User user = userService.findByUsername(principal.getName());
        Order order = orderService.newOrder(user, name, phone, address);
        cart.clear();
        return "redirect:/orders";
    }

}
