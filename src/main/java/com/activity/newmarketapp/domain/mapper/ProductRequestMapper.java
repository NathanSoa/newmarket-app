package com.activity.newmarketapp.domain.mapper;

import com.activity.newmarketapp.data.entities.Product;
import com.activity.newmarketapp.presentation.dtos.ProductRequest;

public class ProductRequestMapper implements Mapper<Product, ProductRequest> {

    @Override
    public Product toEntity(ProductRequest productRequest) {
        return Product.builder()
                        .name(productRequest.name())
                        .description(productRequest.description())
                        .price(productRequest.price())
                        .active(productRequest.active())
                        .build();
    }

    @Override
    public ProductRequest toDTO(Product entity) {
        return new ProductRequest(entity.getName(), entity.getDescription(), entity.getPrice(), entity.getActive());
    }
}
