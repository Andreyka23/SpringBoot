package com.geekbrains.myboot.market.services;

import com.geekbrains.myboot.market.models.Order;
import com.geekbrains.myboot.market.models.OrderItem;
import com.geekbrains.myboot.market.models.User;
import com.geekbrains.myboot.market.repositories.OrderRepository;
import com.geekbrains.myboot.market.utils.Cart;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
