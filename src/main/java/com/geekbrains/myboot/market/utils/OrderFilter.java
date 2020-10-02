package com.geekbrains.myboot.market.utils;

import com.geekbrains.myboot.market.models.Order;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

@Getter
public class OrderFilter {
    private Specification<Order> spec;
    private String filterDefinition;

    public OrderFilter(Map<String, String> params) {
        StringBuilder filterDefinitionBuilder = new StringBuilder();
        spec = Specification.where(null);
        filterDefinition = filterDefinitionBuilder.toString();
    }
}
