package com.activity.newmarketapp.domain.mapper;

import com.activity.newmarketapp.data.entities.ProductItem;
import com.activity.newmarketapp.presentation.dtos.productitem.ProductItemResponse;

public class ProductItemResponseMapper {

    public ProductItemResponse toDTO(ProductItem entity) {
        return new ProductItemResponse(entity.getProduct().getName(), entity.getProduct().getDescription(), entity.getProduct().getPrice(), entity.getQuantity(), entity.getProduct().getPhoto());
    }
}
