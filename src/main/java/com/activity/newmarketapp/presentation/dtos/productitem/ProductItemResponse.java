package com.activity.newmarketapp.presentation.dtos.productitem;

import java.math.BigDecimal;
import java.util.Set;

public record ProductItemResponse(String name,
                                  String description,
                                  BigDecimal price,
                                  Integer quantity,
                                  String photo,
                                  Set<String> categories) {
}
