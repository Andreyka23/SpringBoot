package ru.geekbrains.products.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.products.service.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}