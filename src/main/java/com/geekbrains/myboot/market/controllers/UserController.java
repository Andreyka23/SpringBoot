package com.geekbrains.myboot.market.controllers;

import com.geekbrains.myboot.market.models.User;
import com.geekbrains.myboot.market.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class UserController {
    private UserService userService;


    @GetMapping("/register")
    public String register(Model model) {
        return "register";
    }

    @PostMapping("/register")
    public String new_register(
                              @RequestParam(name = "username") String username,
                              @RequestParam(name = "email") String email,
                              @RequestParam(name = "password") String password ) {
        User newUser = userService.registerUser(username, email, password);
        return "redirect:/orders";
    }

}
