package com.geekbrains.myboot.market.services;

import com.geekbrains.myboot.market.dto.OrderDto;
import com.geekbrains.myboot.market.models.Order;
import com.geekbrains.myboot.market.models.OrderItem;
import com.geekbrains.myboot.market.models.User;
import com.geekbrains.myboot.market.repositories.OrderRepository;
import com.geekbrains.myboot.market.soap.orders.OrderItemsSoap;
import com.geekbrains.myboot.market.soap.orders.OrderSoap;
import com.geekbrains.myboot.market.utils.Cart;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;
    private Cart cart;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Page<Order> findAll(int page, int size) {
        return orderRepository.findAll(PageRequest.of(page, size));
    }

    public List<Order> findByUser(User user) {
        return orderRepository.findOrdersByUserEquals(user);
    }

    public List<OrderDto> findAllUserOrdersDtosByUsername(String username) {
        return orderRepository.findAllOrdersByUsername(username).stream().map(OrderDto::new).collect(Collectors.toList());
    }

    public Order newOrder(User user, String name, String phone, String address) {
        Order order = new Order();
        order.setUser(user);
        order.setPrice(cart.getPrice());
        order.setPhone(phone);
        order.setAddress(address);
        for (OrderItem o : cart.getItems()) {
            o.setOrder(order);
        }
        order.setItems(cart.getItems());
        orderRepository.save(order);
        return order;
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }
    public List<OrderSoap> findSoapByUsername(String username) {
        return orderRepository.findAllOrdersByUsernameSoap(username).stream().map(this::mapOrderToSoap).collect(Collectors.toList());
    }

    private OrderSoap mapOrderToSoap(Order order) {
        OrderSoap orderSoap = new OrderSoap();
        orderSoap.setId(order.getId());
        orderSoap.setUsername(order.getUser().getUsername());
        orderSoap.setPrice(order.getPrice());
        orderSoap.setPhone(order.getPhone());
        orderSoap.setAddress(order.getAddress());
        orderSoap.getOrderItems().addAll(order.getItems().stream().map(this::mapOrderItemToSoap).collect(Collectors.toList()));
        return orderSoap;
    }

    private OrderItemsSoap mapOrderItemToSoap(OrderItem orderItem) {
        OrderItemsSoap orderItemsSoap = new OrderItemsSoap();
        orderItemsSoap.setPrice(orderItem.getPrice());
        orderItemsSoap.setQuantity(orderItem.getQuantity());
        orderItemsSoap.setProduct(orderItem.getProduct().getTitle());
        return orderItemsSoap;
    }

}
