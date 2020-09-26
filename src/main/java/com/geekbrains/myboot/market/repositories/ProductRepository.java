package com.geekbrains.myboot.market.repositories;

import com.geekbrains.myboot.market.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> getProductByPriceGreaterThanEqualAndPriceLessThanEqual(int min, int max, Pageable var);

    Page<Product> getProductByPriceGreaterThanEqual(int min, Pageable var);

    Page<Product> getProductByPriceLessThanEqual(int max, Pageable var);
}
