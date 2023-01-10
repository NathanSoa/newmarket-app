package com.activity.newmarketapp.domain.mapper;

import com.activity.newmarketapp.data.entities.Category;
import com.activity.newmarketapp.data.entities.ProductItem;
import com.activity.newmarketapp.presentation.dtos.productitem.ProductItemResponse;

import java.util.Set;
import java.util.stream.Collectors;

public class ProductItemResponseMapper {

    public ProductItemResponse toDTO(ProductItem entity) {
        Set<String> categories = entity.getProduct().getCategories().stream().map(Category::getName).collect(Collectors.toSet());
        return new ProductItemResponse(entity.getProduct().getName(), entity.getProduct().getDescription(), entity.getProduct().getPrice(), entity.getQuantity(), entity.getProduct().getPhoto(), categories);
    }
}
