package com.geekbrains.myboot.market.controllers;

import com.geekbrains.myboot.market.exceptions.ResourceNotFoundException;
import com.geekbrains.myboot.market.models.Order;
import com.geekbrains.myboot.market.models.Product;
import com.geekbrains.myboot.market.services.OrderService;
import com.geekbrains.myboot.market.utils.Cart;
import com.geekbrains.myboot.market.utils.ProductFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;
    private Cart cart;

    @GetMapping
    public String showAllOrders(Model model, @RequestParam(defaultValue = "1", name = "p") Integer page ) {
        if (page < 1) {
            page = 1;
        }
        Page<Order> orders = orderService.findAll(page - 1, 5);
        model.addAttribute("orders", orders);
        return "orders";
    }

    @PostMapping("/new")
    public String editProduct(@RequestParam(name = "name") String name, @RequestParam(name = "phone") String phone, @RequestParam(name = "address") String address ) {
        Order order = orderService.newOrder(name, phone, address);
        cart.clear();
        return "redirect:/orders";
    }

    /*
    @GetMapping
    public String firstRequest(Model model) {
        model.addAttribute("orders", orderService.findAll());
        return "orders";
    }
     */
}
