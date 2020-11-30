package ru.geekbrains.products.service.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.dtos.ProductDto;
import ru.geekbrains.products.service.models.Product;
import ru.geekbrains.products.service.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    private static final Function<Product, ProductDto> mapToDto = (product) -> {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setPrice(product.getPrice());
        return productDto;
    };

    public List<ProductDto> getProductsByIdList(Long... ids) {
        List<ProductDto> products = new ArrayList<>();
        for (Long id : ids) {
            products.add(productRepository.findById(id).map(mapToDto).orElseThrow());
        }
        return products;
    }
}
