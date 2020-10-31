package com.geekbrains.myboot.market.dto;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;
}
