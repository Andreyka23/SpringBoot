package ru.geekbrains.dtos;

import lombok.Data;

@Data
public class OrderItemDto {
    private Long id;
    private Long productId;
    private int quantity;
    private Float pricePerProduct;
    private Float price;
    private String productTitle;
}
