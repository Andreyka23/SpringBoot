package com.geekbrains.myboot.market.repositories;

import com.geekbrains.myboot.market.models.Order;
import com.geekbrains.myboot.market.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findOrdersByUserEquals(User user);
}
