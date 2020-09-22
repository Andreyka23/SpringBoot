package com.geekbrains.myboot.market.services;

import com.geekbrains.myboot.market.models.Product;
import com.geekbrains.myboot.market.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;


    public Long countProducts() {
        return productRepository.count();
    }

    public Page<Product> findAll(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size));
    }

    public Page<Product> findWithFilter(int page, int size, int min, int max) {
        return productRepository.findWithFilter(PageRequest.of(page, size), min, max);
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }
}
