package com.activity.newmarketapp.presentation.dtos.productitem;

import java.math.BigDecimal;

public record ProductItemResponse(String name,
                                  String description,
                                  BigDecimal price,
                                  Integer quantity,
                                  String photo) {
}
