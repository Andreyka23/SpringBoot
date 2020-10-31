package com.geekbrains.myboot.market.dto;

import com.geekbrains.myboot.market.models.Order;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class OrderDto {
    private List<OrderItemDto> items;
    private int price;
    private String address;

    public OrderDto(Order o) {
        this.items = o.getItems().stream().map(OrderItemDto::new).collect(Collectors.toList());
        this.price = o.getPrice();
        this.address = o.getAddress();
    }
}
