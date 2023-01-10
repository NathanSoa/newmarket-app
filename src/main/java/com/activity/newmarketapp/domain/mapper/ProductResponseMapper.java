package com.activity.newmarketapp.domain.mapper;

import com.activity.newmarketapp.data.entities.Category;
import com.activity.newmarketapp.data.entities.Product;
import com.activity.newmarketapp.presentation.dtos.product.ProductResponse;

import java.util.Set;
import java.util.stream.Collectors;

public class ProductResponseMapper implements Mapper<Product, ProductResponse> {

    @Override
    public Product toEntity(ProductResponse productResponse) {
        Product product = Product.builder()
                .name(productResponse.name())
                .description(productResponse.description())
                .photo(productResponse.photo())
                .price(productResponse.price())
                .active(productResponse.active())
                .build();

        product.setId(productResponse.id());
        return product;
    }

    @Override
    public ProductResponse toDTO(Product entity) {
        Set<String> categories = entity.getCategories().stream().map(Category::getName).collect(Collectors.toSet());
        return new ProductResponse(entity.getId(), entity.getName(), entity.getDescription(), entity.getPhoto(), entity.getPrice(), entity.getActive(), categories);
    }
}
