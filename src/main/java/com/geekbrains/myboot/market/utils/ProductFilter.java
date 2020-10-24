package com.geekbrains.myboot.market.utils;

import com.geekbrains.myboot.market.models.Product;
import com.geekbrains.myboot.market.repositories.specifications.ProductSpecifications;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

@Getter
public class ProductFilter {
    private Specification<Product> spec;
    private String filterDefinition;

    public ProductFilter(Map<String, String> params) {
        StringBuilder filterDefinitionBuilder = new StringBuilder();
        spec = Specification.where(null);

        String filterTitle = params.get("title");
        if (filterTitle != null && !filterTitle.isBlank()) {
            spec = spec.and(ProductSpecifications.titleLike(filterTitle));
            filterDefinitionBuilder.append("&title=").append(filterTitle);
        }

        if (params.containsKey("min_price") && !params.get("min_price").isBlank()) {
            Integer minPrice = Integer.parseInt(params.get("min_price"));
            spec = spec.and(ProductSpecifications.priceGreaterOrEqualsThan(minPrice));
            filterDefinitionBuilder.append("&min_price=").append(minPrice);
        }

        if (params.containsKey("max_price") && !params.get("max_price").isBlank()) {
            Integer maxPrice = Integer.parseInt(params.get("max_price"));
            spec = spec.and(ProductSpecifications.priceLesserOrEqualsThan(maxPrice));
            filterDefinitionBuilder.append("&max_price=").append(maxPrice);
        }

        if (params.containsKey("category") && !params.get("category").isBlank()) {
            Integer category_id = Integer.parseInt(params.get("category"));
            spec = spec.and(ProductSpecifications.categoryEqual(category_id));
            filterDefinitionBuilder.append("&category=").append(category_id);
        }

        filterDefinition = filterDefinitionBuilder.toString();
    }
}
