package ru.geekbrains.orders.service.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.dtos.OrderDto;
import ru.geekbrains.dtos.OrderItemDto;
import ru.geekbrains.dtos.ProductDto;
import ru.geekbrains.orders.service.controllers.ProductRequest;
import ru.geekbrains.orders.service.models.Order;
import ru.geekbrains.orders.service.models.OrderItem;
import ru.geekbrains.orders.service.repository.OrderRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRequest productRequest;

    private final Function<Order, OrderDto> mapToDto = (order) -> {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setPrice(order.getPrice());
        orderDto.setItems(order.getItems().stream().map(this::mapOrderItemToDto).collect(Collectors.toList()));
        return orderDto;
    };

    public List<OrderDto> findAllOrders() {
        return orderRepository.findAll().stream().map(mapToDto).collect(Collectors.toList());
    }

    public Optional<OrderDto> findById(Long id) {
        return orderRepository.findById(id).map(mapToDto);
    }

    public void createOrder(Long... productIds) {
        List<OrderItem> orderItems = productRequest.getProductsByIds(productIds).stream()
                .map(OrderItem::new).collect(Collectors.toList());
        Float sumPrice = 0f;
        for (OrderItem orderItem : orderItems) {
            sumPrice += orderItem.getPrice();
        }
        Order order = new Order(orderItems, sumPrice);
        orderRepository.save(order);
    }

    private OrderItemDto mapOrderItemToDto(OrderItem orderItem) {
        OrderItemDto orderItemDto = new OrderItemDto();
        ProductDto productDto = productRequest.getProductsByIds(orderItem.getProductId()).get(0);
        orderItemDto.setId(orderItem.getId());
        orderItemDto.setPrice(orderItem.getPrice());
        orderItemDto.setPricePerProduct(orderItem.getPricePerProduct());
        orderItemDto.setProductId(orderItem.getProductId());
        orderItemDto.setQuantity(orderItem.getQuantity());
        orderItemDto.setProductTitle(productDto.getTitle());
        return orderItemDto;
    }

}
