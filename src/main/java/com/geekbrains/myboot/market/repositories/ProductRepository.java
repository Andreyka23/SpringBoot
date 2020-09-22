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

    @Query("select p from Product p where p.price > :min and p.price < :max ")
    Page<Product> findWithFilter(Pageable pag, @Param("min") int min, @Param("max") int max);
}
