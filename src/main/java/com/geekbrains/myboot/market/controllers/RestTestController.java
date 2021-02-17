package com.geekbrains.myboot.market.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
@AllArgsConstructor
public class RestTestController {

    @GetMapping
    public String getRequest() {
        return "Success Get request";
    }
    
    @PostMapping
    public String postRequest() {
        return "Success Post request";
    }

}
