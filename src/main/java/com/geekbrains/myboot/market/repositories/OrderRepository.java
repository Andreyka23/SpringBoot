package com.geekbrains.myboot.market.repositories;

import com.geekbrains.myboot.market.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
