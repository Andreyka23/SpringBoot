package ru.geekbrains.orders.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.orders.service.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
