package com.geekbrains.myboot.market.services;

import com.geekbrains.myboot.market.models.Product;
import com.geekbrains.myboot.market.repositories.ProductRepository;
import com.geekbrains.myboot.market.soap.products.ProductSoap;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public void deleteAll() {
        productRepository.deleteAll();
    }

    public Page<Product> findAll(Specification<Product> spec, int page, int size) {
        return productRepository.findAll(spec, PageRequest.of(page, size));
    }

    public void editProduct(Product product, String title, int price) {
        product.setTitle(title);
        product.setPrice(price);
        productRepository.save(product);
    }

    public Product saveOrUpdate(Product product) {
        return productRepository.save(product);
    }


    public List<ProductSoap> findAllSOAP() {
        return productRepository.findAll().stream().map(this::mapToSoap).collect(Collectors.toList());
    }

    private ProductSoap mapToSoap(Product product) {
        ProductSoap soapProduct = new ProductSoap();
        soapProduct.setId(product.getId());
        soapProduct.setPrice(product.getPrice());
        soapProduct.setTitle(product.getTitle());
        soapProduct.setCategory(product.getCategory().getTitle());
        return soapProduct;
    }

}
